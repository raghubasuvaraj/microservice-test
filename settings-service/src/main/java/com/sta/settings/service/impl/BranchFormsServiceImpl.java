package com.sta.settings.service.impl;

import com.sta.settings.dto.request.BranchFormsRequest;
import com.sta.settings.dto.response.BranchFormsResponse;
import com.sta.settings.dto.response.ByBranchOrMasterFormResponse;
import com.sta.settings.entities.BranchFormsEntity;
import com.sta.settings.entities.BranchInfoEntity;
import com.sta.settings.entities.MasterFormsEntity;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.repository.BranchFormsRepository;
import com.sta.settings.repository.BranchInfoRepository;
import com.sta.settings.repository.MasterFormsRepository;
import com.sta.settings.service.BranchFormsService;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of all CRUD operation of AssignFormToBranchEntity.
 * 
 * @author r@ghu
 *
 */
@Slf4j
@Service("AssignFormService")
public class BranchFormsServiceImpl implements BranchFormsService {

	@Autowired
	private BranchFormsRepository branchFormsRepository;

	@Autowired
	private BranchInfoRepository branchRepository;

	@Autowired
	private MasterFormsRepository masterFormsRepository;

	/**
	 * Assigning master-form to the branch.
	 * <p>
	 * Cannot find the entity of master-form, once it got confirmed, just like
	 * branch master form will also identified as foreign key.
	 *
	 * @param formRequest
	 * @return
	 */
	@Override
	public BranchFormsEntity createAssignFormToBranch(BranchFormsRequest formRequest) throws SettingsException {
		try {
			if (formRequest.getMasterFormUrl() == null || formRequest.getMasterFormUrl().isEmpty()) {
				throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
						"Form URL is mandatory.",
						HttpStatus.BAD_REQUEST, null);
			}

			if (formRequest.getBranchId() == null || formRequest.getBranchId().isEmpty()) {
				throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
						"Branch is mandatory.",
						HttpStatus.BAD_REQUEST, null);
			}

			String formUrl = formRequest.getMasterFormUrl();

			BranchInfoEntity branchInfoEntity = branchRepository.findByIdAndActive(formRequest.getBranchId(), true)
					.orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.BRANCH_NOT_FOUND,
							HttpStatus.NOT_FOUND, null));

			List<MasterFormsEntity> masterForms = masterFormsRepository.findAll();

			BranchFormsEntity branchFormEntityToBeSaved;
			MasterFormsEntity masterFormEntityToBeSaved = null;

			if (formRequest.getMasterFormId() != null && !formRequest.getMasterFormId().isEmpty()) {
				Optional<MasterFormsEntity> optionalMasterForm = masterForms.stream().
						filter(masterForm -> formRequest.getMasterFormId().equalsIgnoreCase(masterForm.getId())).
						findAny();
				if (optionalMasterForm.isPresent()) {
					MasterFormsEntity masterForm = optionalMasterForm.get();
					BranchFormsEntity branchFormEntity = new BranchFormsEntity();
					branchFormEntity.setMasterFormsEntity(masterForm);
					branchFormEntity.setBranchInfoEntity(branchInfoEntity);
					branchFormEntity.setFormUrl(formUrl);
					branchFormEntityToBeSaved = branchFormEntity;
				} else {
					throw new SettingsException(ErrorCodesConstants.NOT_FOUND, "Master Form Not Found.",
							HttpStatus.NOT_FOUND, null);
				}
			} else {
				if ((formRequest.getName().length() > 50 || formRequest.getName().length() < 2)
						|| !(formRequest.getName().matches("^[a-z A-Z0-9]+-?[a-z A-Z0-9]+$"))) {
					throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
							"Form name should be alphanumeric with one (-) allowed, and of 2-50 characters." + ": " + formRequest.getName(),
							HttpStatus.BAD_REQUEST, null);
				}
				if ((formRequest.getCode().length() > 10 || formRequest.getCode().length() < 2)
						|| !(formRequest.getCode().matches("^[a-z A-Z0-9]+-?[a-z A-Z0-9]+$"))) {
					throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
							"Form short code should be alphanumeric with one (-) allowed, of 2-10 characters." + ": " + formRequest.getCode(),
							HttpStatus.BAD_REQUEST, null);
				}

				MasterFormsEntity masterFormsEntity = new MasterFormsEntity();
				masterFormsEntity.setName(formRequest.getName());
				masterFormsEntity.setShortCode(formRequest.getCode());

				BranchFormsEntity branchFormEntity = new BranchFormsEntity();
				branchFormEntity.setMasterFormsEntity(masterFormsEntity);
				branchFormEntity.setBranchInfoEntity(branchInfoEntity);
				branchFormEntity.setFormUrl(formUrl);
				masterFormEntityToBeSaved = masterFormsEntity;
				branchFormEntityToBeSaved = branchFormEntity;
			}

			if (masterFormEntityToBeSaved != null) {
				try {
					masterFormsRepository.save(masterFormEntityToBeSaved);
				} catch (DataIntegrityViolationException e) {
					if (e.getCause() instanceof ConstraintViolationException) {
						ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
						String columnName = ex.getConstraintName();
						throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Master form already exists with the form " + columnName.substring(columnName.lastIndexOf('.') + 1),
								HttpStatus.BAD_REQUEST, null);
					}
				}
			}

			try {
				branchFormsRepository.save(branchFormEntityToBeSaved);
			} catch (DataIntegrityViolationException e) {
				if (e.getCause() instanceof ConstraintViolationException) {
					ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
					throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Form is already associated with the given branch.",
							HttpStatus.BAD_REQUEST, null);
				}
			}
			return branchFormEntityToBeSaved;
		} catch (SettingsException se) {
			throw se;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.ASSIGNE_FORM_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Update the master-form which is assigned to a branch.
	 *
	 * @param request
	 * @return
	 */
	@Override
	public BranchFormsEntity updateAssignFormToBranch(BranchFormsRequest request) {
		try {
			if (!branchFormsRepository.existsById(request.getId()))
				throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.ASSIGNE_FORM_NOT_FOUND,
						HttpStatus.NOT_FOUND, null);

			if (!branchRepository.existsById(request.getBranchId()))
				throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.BRANCH_NOT_FOUND,
						HttpStatus.NOT_FOUND, null);

			if (!masterFormsRepository.existsById(request.getMasterFormId()))
				throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.MASTER_FORM_NOT_FOUND,
						HttpStatus.NOT_FOUND, null);

			if (branchFormsRepository.existsByBranchInfoEntityIdAndMasterFormsEntityIdAndActiveAndIdNot(
					request.getBranchId(), request.getMasterFormId(), true, request.getId()))
				throw new SettingsException(ErrorCodesConstants.CONFLICT, MessageConstants.ASSIGNE_FORM_ALREADY_EXIST,
						HttpStatus.CONFLICT, null);


			log.info("All checking completed, update assignig of form is beginning...");
			Long currentTime = new Date().getTime();

			log.info("Branch & master-form details is fetching.");
			BranchInfoEntity branch = branchRepository.getReferenceById(request.getBranchId());
			MasterFormsEntity masterForms = masterFormsRepository.getReferenceById(request.getMasterFormId());

			log.info("Updating assigning master-form to the branch.");
			BranchFormsEntity assignForm = branchFormsRepository.getReferenceById(request.getId());
			assignForm.setBranchInfoEntity(branch);
			assignForm.setMasterFormsEntity(masterForms);
			assignForm.setFormUrl(request.getMasterFormUrl());
			//assignForm.setLastModifiedAt(currentTime);
			assignForm = branchFormsRepository.save(assignForm);
			log.info("Updated assigning of master-form to the branch: ", branch.getBranchName());
			return assignForm;
		} catch (SettingsException se) {
			throw se;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.ASSIGNE_FORM_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Unassigns or delete the master-forms from the branch.
	 * 
	 * @param branchId
	 * @return
	 */
	@Override
	public Boolean unAssignFormFromTheBranch(String branchId) {
		try {
			if (!branchRepository.existsById(branchId))
				throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.BRANCH_NOT_FOUND,
						HttpStatus.NOT_FOUND, null);
			log.info("All checking completed, unassiging of form is beginning...");
			int deleted = branchFormsRepository.setActiveStatus(false, LocalDateTime.now(), branchId, CommonStatus.DELETE);
			log.info(deleted > 0 ? "Master unassigning completed." : "Failed to unassigning.");
			return deleted > 0;

		} catch (SettingsException se) {
			throw se;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.ASSIGNE_FORM_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Fetch the assigned master-form under a branch
	 *
	 * @param branchId
	 * @return
	 */
	@Override
	public List<ByBranchOrMasterFormResponse> getAllAssignedFormsByBranchId(String branchId) {
		if (!branchRepository.existsById(branchId))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.BRANCH_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);
		try {
			List<ByBranchOrMasterFormResponse> responseList=new ArrayList<>();

			log.info("Branch details is fetching.");
			BranchInfoEntity branch = branchRepository.getReferenceById(branchId);
			log.info("Bring all the assigned form by branch id");

			List<BranchFormsEntity> allAssignedForm = branchFormsRepository
					.findAllByBranchInfoEntityIdAndActive(branch.getId(), true);

			if (!CollectionUtils.isEmpty(allAssignedForm)) {
				log.info("Listed all the assigned forms by branch id");
				responseList = allAssignedForm.stream().map(form -> new ByBranchOrMasterFormResponse(form.getMasterFormsEntity().getId(), form.getMasterFormsEntity().getName()
						, null)).toList();
			}
			return responseList;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.ASSIGNE_FORM_GET_BY_BARANCH_ID_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Get the branch list by master form id.
	 * <p>
	 * format: <blockquote>
	 * 
	 * <pre>
	 *  
	 *    [
	 *        {
	 *            "masterFormId" : "master0001",
	 *            "masterFormUrl" : "https://xd.adobe.com/view/ad7e7408-892e",
	 *            "branches": [
	 *                {
	 *                    "branchId": "0uy6787t",
	 *                    "branch": "branch-001"
	 *                }
	 *            ]
	 *        } 
	 *    ]
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 * 
	 * @see <a href=
	 *      "https://xd.adobe.com/view/ad7e7408-892e-4573-8840-b472ac72999a-f82d/screen/83cd5646-0175-4a28-bc3a-bcd0930aa8b8">DPS
	 *      Layout - 77</a>
	 * 
	 * @return
	 */
	@Override
	public List<ByBranchOrMasterFormResponse> getAllFormsWithBranches(String formName) {
		try {
			List<Predicate> predicates = new ArrayList<>();

			Specification<MasterFormsEntity> spec = (root, query, criteriaBuilder) -> {
				predicates.add(criteriaBuilder.equal(root.get("active"), true));

				if(formName!=null && !formName.isEmpty()){
					predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
							"%" + formName.toLowerCase() + "%"));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			};

			List<MasterFormsEntity> masterFormsList = masterFormsRepository.findAll(spec);

			List<ByBranchOrMasterFormResponse> responseList = new ArrayList<>();

			if(!masterFormsList.isEmpty()) {
				List<String> masterFormIds = masterFormsList.stream().map(masterForm -> masterForm.getId()).collect(Collectors.toList());

				List<BranchFormsEntity> branchFormsList = branchFormsRepository.findByActiveAndMasterFormsEntityIdIn(true,masterFormIds);

				masterFormsList.forEach(masterForm -> {
					ByBranchOrMasterFormResponse formResponse = new ByBranchOrMasterFormResponse();
					formResponse.setMasterFormId(masterForm.getId());
					formResponse.setMasterForm(masterForm.getName());
					List<BranchFormsResponse> assignedForms = new ArrayList<>();
					branchFormsList.forEach(branchForm -> {
						if (branchForm.getMasterFormsEntity().getId().equalsIgnoreCase(masterForm.getId())) {
							BranchFormsResponse branchFormsResponse = new BranchFormsResponse();
							branchFormsResponse.setBranchId(branchForm.getBranchInfoEntity().getId());
							branchFormsResponse.setBranch(branchForm.getBranchInfoEntity().getBranchName());
							branchFormsResponse.setMasterFormUrl(branchForm.getFormUrl());
							assignedForms.add(branchFormsResponse);
						}
					});
					formResponse.setAssignedForms(assignedForms);
					responseList.add(formResponse);
				});
			}
			return responseList;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.ASSIGNE_FORM_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

}
