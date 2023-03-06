package com.sta.settings.service.impl;

import java.time.LocalDateTime;
import java.util.*;

import com.sta.settings.dto.request.DepartmentRequestList;
import com.sta.settings.entities.InstituteDepartmentsEntity;
import com.sta.settings.entities.InstitutesEntity;
import com.sta.settings.entities.MasterDepartmentsEntity;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.repository.InstituteDepartmentsRepository;
import com.sta.settings.repository.MasterDepartmentsRepository;
import com.sta.settings.service.IDepartmentsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import com.sta.settings.dto.request.ActiveStatusToggleRequest;
import com.sta.settings.dto.request.DepartmentsRequest;
import com.sta.settings.dto.response.DepartmentsResponse;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.repository.BranchInfoRepository;
import com.sta.settings.repository.InstitutesRepository;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.criteria.Predicate;


@Slf4j
@Service
public class DepartmentsServiceImpl implements IDepartmentsService {

	@Autowired
	private BranchInfoRepository branchRepository;

	@Autowired
	private InstitutesRepository institutesRepository;

	@Autowired
	private MasterDepartmentsRepository masterDepartmentsRepository;

	@Autowired
	private InstituteDepartmentsRepository instituteDepartmentsRepository;

	/**
	 * Checking the duplication, then saving each department by the branch
	 * 
	 */
	@Override
	public List<InstituteDepartmentsEntity> createDepartments(DepartmentRequestList request, String instituteId) {

		try {
			InstitutesEntity institute = institutesRepository.findByIdAndActive(instituteId, true)
					.orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
							HttpStatus.NOT_FOUND, null));

			List<MasterDepartmentsEntity> masterDepartments = masterDepartmentsRepository.findAll();

			List<InstituteDepartmentsEntity> instituteDepartmentListToBeSaved = new ArrayList<>();
			List<MasterDepartmentsEntity> masterDepartmentListToBeSaved = new ArrayList<>();

			for (DepartmentsRequest departmentReq : request.getDepartments()) {
				if (departmentReq.getMasterDepartmentId() != null && !departmentReq.getMasterDepartmentId().isEmpty()) {
					Optional<MasterDepartmentsEntity> optionalMasterDepartment = masterDepartments.stream().
							filter(masterDept -> departmentReq.getMasterDepartmentId().equalsIgnoreCase(masterDept.getId())).
							findAny();
					if (optionalMasterDepartment.isPresent()) {
						MasterDepartmentsEntity masterDepartment = optionalMasterDepartment.get();
						InstituteDepartmentsEntity instituteDepartment = new InstituteDepartmentsEntity();
						instituteDepartment.setInstitute(institute);
						instituteDepartment.setMasterDepartment(masterDepartment);
						instituteDepartmentListToBeSaved.add(instituteDepartment);
					} else {
						throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.MASTER_DEPARTMENT_NOT_FOUND,
								HttpStatus.NOT_FOUND, null);
					}
				} else {

					if ((departmentReq.getName().length() > 50 || departmentReq.getName().length() < 2)
							|| !(departmentReq.getName().matches("^[a-z A-Z0-9]+-?[a-z A-Z0-9]+$"))) {
						throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
								"Department name should be alphanumeric with one (-) allowed, and of 2-50 characters." + ": " + departmentReq.getName(),
								HttpStatus.BAD_REQUEST, null);
					}
					if ((departmentReq.getCode().length() > 10 || departmentReq.getCode().length() < 2)
							|| !(departmentReq.getCode().matches("^[a-z A-Z0-9]+-?[a-z A-Z0-9]+$"))) {
						throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
								"Department code should be alphanumeric with one (-) allowed, and of 2-10 characters." + ": " + departmentReq.getCode(),
								HttpStatus.BAD_REQUEST, null);
					}
					MasterDepartmentsEntity masterDepartment = new MasterDepartmentsEntity();
					masterDepartment.setName(departmentReq.getName().toUpperCase());
					masterDepartment.setShortCode(departmentReq.getCode().toUpperCase());

					InstituteDepartmentsEntity instituteDepartment = new InstituteDepartmentsEntity();
					instituteDepartment.setInstitute(institute);
					instituteDepartment.setMasterDepartment(masterDepartment);

					instituteDepartmentListToBeSaved.add(instituteDepartment);
					masterDepartmentListToBeSaved.add(masterDepartment);
				}
			}
			try {
				masterDepartmentsRepository.saveAll(masterDepartmentListToBeSaved);
			} catch (DataIntegrityViolationException e) {
				if (e.getCause() instanceof ConstraintViolationException) {
					ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
					String columnName = ex.getConstraintName();
					throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Duplicate data for " + "department " + columnName.substring(columnName.lastIndexOf('.') + 1),
							HttpStatus.BAD_REQUEST, null);
				}
			}
			try {
				instituteDepartmentsRepository.saveAll(instituteDepartmentListToBeSaved);
			} catch (DataIntegrityViolationException e) {
				if (e.getCause() instanceof ConstraintViolationException) {
					ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
					throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Department is already available for the given institute.",
							HttpStatus.BAD_REQUEST, null);
				}
			}
			return instituteDepartmentListToBeSaved;
		} catch (SettingsException se) {
			throw se;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.DEPARTMENTS_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}
	/**
	 * Update the department
	 * 
	 */
	@Override
	public DepartmentsResponse updateDepartment(DepartmentsRequest request) {
		try {
			if (StringUtils.isBlank(request.getId()))
				throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.DEPARTMENT_ID_MANDATORY,
						HttpStatus.BAD_REQUEST, null);


			// .....to be continued.
			return null;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.DEPARTMENT_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Get by id [specific department]
	 * 
	 */
	@Override
	public InstituteDepartmentsEntity getDepartmentById(String id) {
		if (!instituteDepartmentsRepository.existsById(id))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.DEPARTMENT_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			return instituteDepartmentsRepository.getReferenceById(id);

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.DEPARTMENT_GET_BY_ID_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Get all departments by the filtering of below @param
	 * <p>
	 * Search will enable once the DB and the pagination response confirm.
	 * 
	 */
	@Override
	public List<InstituteDepartmentsEntity> getAllDepartments(String instituteId, String departmentName) {
		try {
			List<Predicate> predicates = new ArrayList<>();

			Specification<InstituteDepartmentsEntity> spec = (root, query, criteriaBuilder) -> {
				predicates.add(criteriaBuilder.equal(root.get("active"), true));
				if (instituteId != null && !instituteId.isEmpty())
					predicates.add(criteriaBuilder.equal(root.get("institute").get("id"), instituteId));

				if (departmentName != null && !departmentName.isEmpty())
					predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("masterCurriculum").get("name")),
							"%" + departmentName.toLowerCase() + "%"));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			};
			return instituteDepartmentsRepository.findAll(spec);
		} catch (SettingsException se) {
			throw se;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.DEPARTMENT_GET_ALL_FAILED,
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}


	@Override
	public Boolean toggleActiveStatus(ActiveStatusToggleRequest request) {
		if (!instituteDepartmentsRepository.existsById(request.getId()))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.DEPARTMENT_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			Long currentTime = new Date().getTime();
			InstituteDepartmentsEntity departmentsEntity = instituteDepartmentsRepository.getReferenceById(request.getId());
			departmentsEntity.setActive(request.isActive());
		//	departmentsEntity.setLastModifiedAt(currentTime);
			departmentsEntity = instituteDepartmentsRepository.save(departmentsEntity);
			boolean response = departmentsEntity.isActive() == request.isActive();
			String status = request.isActive() ? "Active" : "De-active";
			log.info(response ? "Departement status successfully toggled into " + status
					: "Departement status failed to toggle into " + status);
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.DEPARTMENT_STATUS_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public Boolean deleteDepartmentById(String id) {
		if (!instituteDepartmentsRepository.existsById(id))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.DEPARTMENT_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			Long currentTime = new Date().getTime();
			InstituteDepartmentsEntity departmentsEntity = instituteDepartmentsRepository.getReferenceById(id);
			departmentsEntity.setActive(false);
			departmentsEntity.setStatus(CommonStatus.DELETE);
			departmentsEntity.setDeletedAt(LocalDateTime.now());
			departmentsEntity = instituteDepartmentsRepository.save(departmentsEntity);
			boolean response = Boolean.FALSE.equals(departmentsEntity.isActive());
			log.info(response ? "Department successfully deleted." : "Failed to delete the department.");
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.DEPARTMENT_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

}
