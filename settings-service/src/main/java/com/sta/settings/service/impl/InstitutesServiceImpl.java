package com.sta.settings.service.impl;

import java.util.Date;
import java.util.List;

import com.sta.settings.entities.MasterInstituteGroups;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.service.IInstitutesService;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.google.common.base.Enums;
import com.sta.settings.dto.request.ActiveStatusToggleRequest;
import com.sta.settings.dto.request.InstitutesApproveRequest;
import com.sta.settings.dto.request.InstitutesRequest;
import com.sta.settings.dto.response.InstitutesResponse;
import com.sta.settings.entities.InstitutesEntity;
import com.sta.settings.enums.ApprovedStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.mappers.InstitutesMapper;
import com.sta.settings.repository.InstituteGroupsRepository;
import com.sta.settings.repository.InstitutesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InstitutesServiceImpl implements IInstitutesService {

	@Autowired
	private InstitutesRepository institutesRepository;
	
	@Autowired
	private InstituteGroupsRepository instituteGroupsRepository;

	@Override
	public InstitutesResponse createInstitute(InstitutesRequest request) {
		if (!instituteGroupsRepository.existsById(request.getInstituteGroupId()))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_GROUP_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);
		
		if (institutesRepository.existsByInstituteNameOrInstituteCode(request.getInstituteName(), request.getInstituteCode()))
			throw new SettingsException(ErrorCodesConstants.CONFLICT, MessageConstants.INSTITUTE_ALREADY_EXIST,
					HttpStatus.CONFLICT, null);
		try {
			log.info("All checking completed, institute creating...");
			InstitutesResponse response = null;
			MasterInstituteGroups instituteGroups = instituteGroupsRepository.getReferenceById(request.getInstituteGroupId());
			InstitutesEntity institutesEntity = InstitutesMapper.requestToEntity(request);
			institutesEntity.setInstituteGroups(instituteGroups);
//			institutesEntity.setActiveStatus(false);
			institutesEntity = institutesRepository.save(institutesEntity);
			if (!StringUtils.isBlank(institutesEntity.getId())) {
				log.info("Institute created.");
				response = InstitutesMapper.entityToResponse(institutesEntity);
			}
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public InstitutesResponse updateInstitute(InstitutesRequest request) {
		if (!institutesRepository.existsById(request.getId()))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);
		
		if (!instituteGroupsRepository.existsById(request.getInstituteGroupId()))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_GROUP_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		if (institutesRepository.existsByInstituteNameAndIdNot(request.getInstituteName(), request.getId())
				|| institutesRepository.existsByInstituteCodeAndIdNot(request.getInstituteCode(), request.getId()))
			throw new SettingsException(ErrorCodesConstants.CONFLICT, MessageConstants.INSTITUTE_ALREADY_EXIST,
					HttpStatus.CONFLICT, null);

		try {
			log.info("All checking completed, institute updating...");
			InstitutesResponse response = null;
			MasterInstituteGroups instituteGroups = instituteGroupsRepository.getReferenceById(request.getInstituteGroupId());
			InstitutesEntity existingInstitute = institutesRepository.getReferenceById(request.getId());
			InstitutesEntity institutesEntity = InstitutesMapper.updateRequestToEntity(request, existingInstitute.getId());
			institutesEntity.setInstituteGroups(instituteGroups);
			institutesEntity = institutesRepository.save(institutesEntity);
			if (!ObjectUtils.isEmpty(institutesEntity)) {
				log.info("Institute updated.");
				response = InstitutesMapper.entityToResponse(institutesEntity);
			}
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public InstitutesResponse getInstituteById(String id) {
		if (StringUtils.isBlank(id) || !institutesRepository.existsById(id))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			log.info("Get the institute by id");
			InstitutesEntity institutesEntity = institutesRepository.getReferenceById(id);
			return InstitutesMapper.entityToResponse(institutesEntity);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_GET_BY_ID_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public List<InstitutesResponse> getAllInstitute() {
		try {
			return institutesRepository.findAllInstitutes();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public Boolean toggleActiveStatus(ActiveStatusToggleRequest request) {
		if (StringUtils.isBlank(request.getId()) || !institutesRepository.existsById(request.getId()))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			Long currentTime = new Date().getTime();
			boolean response = false;
			String status = request.isActive() ? "Active." : "De-active.";
			InstitutesEntity institutesEntity = institutesRepository.getReferenceById(request.getId());
		//	institutesEntity.setLastModifiedAt(currentTime);
			institutesEntity.setActive(request.isActive());
			institutesEntity = institutesRepository.save(institutesEntity);
			response = (institutesEntity.isActive() == request.isActive());
			if (response)
				log.info("Toggled the active status of institute into " + status);
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_STATUS_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public Boolean deleteInstitute(String id) {
		if (StringUtils.isBlank(id) || !institutesRepository.existsById(id))
			throw new SettingsException(HttpStatus.NOT_FOUND.toString(), "Institute not exist.", HttpStatus.NOT_FOUND, null);

		try {
			Long currentTime = new Date().getTime();
			InstitutesEntity institutesEntity = institutesRepository.getReferenceById(id);
			//institutesEntity.setLastModifiedAt(currentTime);
			institutesEntity.setActive(false);
			institutesEntity = institutesRepository.save(institutesEntity);
			if (Boolean.FALSE.equals(institutesEntity.isActive()))
				log.info("Institute deleted successfully.");
			return !institutesEntity.isActive();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Approve/Reject the institute.
	 * 
	 * @param
	 * @param request
	 * @return
	 */
	@Override
	public InstitutesResponse approveOrRejectInstitute(InstitutesApproveRequest request) {
		log.info("Institute status changing into " + request.getApprovedStatus() + "...");

		if (!Enums.getIfPresent(ApprovedStatus.class, request.getApprovedStatus()).isPresent())
			throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.INVALID_STATUS,
					HttpStatus.BAD_REQUEST, null);

		if (!institutesRepository.existsById(request.getId()))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			InstitutesResponse response = null;
			InstitutesEntity institutesEntity = institutesRepository.getReferenceById(request.getId());
			String oldStatus = institutesEntity.getStatus().getValue();

			institutesEntity.setStatus(CommonStatus.valueOf(request.getApprovedStatus()));
			if (ApprovedStatus.valueOf(request.getApprovedStatus()) == ApprovedStatus.APPROVED) {
				log.info("Institute approved and status turning to active");
				institutesEntity.setActive(true);
			}
			if (!StringUtils.isBlank(request.getComments()))
				institutesEntity.setComments(request.getComments());

			institutesEntity = institutesRepository.save(institutesEntity);
			if (!oldStatus.equals(institutesEntity.getStatus().getCode())) {
				log.info("Institute status changed to " + request.getApprovedStatus());
				response = InstitutesMapper.entityToResponse(institutesEntity);
			}
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_STATUS_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

}