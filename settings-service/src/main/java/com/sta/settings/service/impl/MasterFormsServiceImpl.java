package com.sta.settings.service.impl;

import com.sta.settings.repository.MasterFormsRepository;
import com.sta.settings.service.MasterFormsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic definition goes here for master-forms
 * 
 * @author r@ghu
 *
 */
@Slf4j
@Service("masterFormsService")
public class MasterFormsServiceImpl implements MasterFormsService {

	@Autowired
	private MasterFormsRepository masterFormsRepository;

//	@Autowired
//	private CurriculumRepository curriculumRepository;

	/**
	 * Creating a master form under a curriculum. Screen is not sure so going with
	 * one at a time.
	 * 
	 * @param request
	 * @return
	 */
//	@Override
//	public MasterFormsResponse createMasterForms(MasterFormsRequest request) {
//		if (request.getId() != null)
//			throw new SettingsException(ErrorCodesConstants.METHOD_NOT_ALLOWED,
//					MessageConstants.PUT_OPERATION_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED, null);
//
//		if (!Enums.getIfPresent(ApprovedStatus.class, request.getStatus()).isPresent())
//			throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.INVALID_STATUS,
//					HttpStatus.BAD_REQUEST, null);
//
//		if (masterFormsRepository.existsByNameAndShortCodeAndCurriculumsEntityIdAndActive(request.getName(),
//				request.getShortCode(), request.getCurriculumId(), true))
//			throw new SettingsException(ErrorCodesConstants.CONFLICT, MessageConstants.MASTER_FORM_ALREADY_EXIST,
//					HttpStatus.CONFLICT, null);
//
//		if (!curriculumRepository.existsById(request.getCurriculumId()))
//			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.CURRICULUM_NOT_FOUND,
//					HttpStatus.NOT_FOUND, null);
//
//		try {
//			log.info("All checking completed, prodceeding to create master-form...");
//			MasterFormsResponse response = null;
//			CurriculumsEntity curriculums = curriculumRepository.getReferenceById(request.getCurriculumId());
//			MasterFormsEntity masterForms = new MasterFormsEntity(request.getName(), request.getShortCode(),
//					curriculums, ApprovedStatus.valueOf(request.getStatus()),true);
//			masterForms = masterFormsRepository.save(masterForms);
//			if (!StringUtils.isBlank(masterForms.getId())) {
//				log.info("Master form created!");
//				response = new MasterFormsResponse(masterForms.getId(), masterForms.getName(),
//						masterForms.getShortCode(), curriculums.getId(), curriculums.getName(),
//						masterForms.getStatus());
//			}
//			log.info("Process completed.");
//			return response;
//		} catch (Exception e) {
//			log.error(ExceptionUtils.getStackTrace(e));
//			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
//					MessageConstants.MASTER_FORM_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
//		}
//	}

	/**
	 * Update master-form by id
	 * 
	 * @param request
	 * @return
	 */
//	@Override
//	public MasterFormsResponse updateMasterForms(MasterFormsRequest request) {
//		if (!masterFormsRepository.existsById(request.getId()))
//			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.MASTER_FORM_NOT_FOUND,
//					HttpStatus.NOT_FOUND, null);
//
//		if (!Enums.getIfPresent(ApprovedStatus.class, request.getStatus()).isPresent())
//			throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.INVALID_STATUS,
//					HttpStatus.BAD_REQUEST, null);
//
//		if (masterFormsRepository.existsByNameAndShortCodeAndCurriculumsEntityIdAndIdNot(request.getName(),
//				request.getShortCode(), request.getCurriculumId(), request.getId()))
//			throw new SettingsException(ErrorCodesConstants.CONFLICT, MessageConstants.MASTER_FORM_ALREADY_EXIST,
//					HttpStatus.CONFLICT, null);
//
//		if (!curriculumRepository.existsById(request.getCurriculumId()))
//			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.CURRICULUM_NOT_FOUND,
//					HttpStatus.NOT_FOUND, null);
//
//		try {
//			log.info("All checking completed, prodceeding to update master-form...");
//			MasterFormsResponse response = null;
//			Long currentTime = new Date().getTime();
//			CurriculumsEntity curriculums = curriculumRepository.getReferenceById(request.getCurriculumId());
//			MasterFormsEntity masterForms = masterFormsRepository.getReferenceById(request.getId());
//			masterForms.setName(request.getName());
//			masterForms.setShortCode(request.getShortCode());
//			masterForms.setCurriculumsEntity(curriculums);
//			masterForms.setStatus(ApprovedStatus.valueOf(request.getStatus()));
//			//masterForms.setLastModifiedAt(currentTime);
//			masterForms = masterFormsRepository.save(masterForms);
//			if (!StringUtils.isBlank(masterForms.getId())) {
//				log.info("Master form updated!");
//				response = new MasterFormsResponse(masterForms.getId(), masterForms.getName(),
//						masterForms.getShortCode(), curriculums.getId(), curriculums.getName(),
//						masterForms.getStatus());
//			}
//			log.info("Process completed.");
//			return response;
//		} catch (Exception e) {
//			log.error(ExceptionUtils.getStackTrace(e));
//			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
//					MessageConstants.MASTER_FORM_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
//		}
//	}

	/**
	 * Getting all the master-form by curriculum
	 * 
	 * @param carriculumId
	 * @return
	 */
//	@Override
//	public List<MasterFormsResponse> getMasterFormByCarriculumn(String carriculumId) {
//		if (!curriculumRepository.existsById(carriculumId))
//			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.CURRICULUM_NOT_FOUND,
//					HttpStatus.NOT_FOUND, null);
//		try {
//			List<MasterFormsResponse> response = new ArrayList<>();
//			log.info("Filtering all the master-forms by carriculum");
//			List<MasterFormsEntity> masterForms = masterFormsRepository
//					.findAllByCurriculumsEntityIdAndActive(carriculumId, true);
//			if (!CollectionUtils.isEmpty(masterForms)) {
//				log.info("Setting all the master-forms by carriculum to a list");
//				response = masterForms.stream().map(item -> new MasterFormsResponse(item.getId(), item.getName(),
//						item.getShortCode(), item.getStatus())).toList();
//			}
//			return response;
//		} catch (Exception e) {
//			log.error(ExceptionUtils.getStackTrace(e));
//			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
//					MessageConstants.MASTER_FORM_GET_BY_CARRICULUM_ID_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
//		}
//	}
}
