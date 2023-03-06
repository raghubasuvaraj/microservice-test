package com.sta.settings.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sta.settings.entities.MasterInstituteGroups;
import com.sta.settings.enums.CommonStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.sta.settings.dto.request.InstituteGroupsRequest;
import com.sta.settings.dto.response.InstituteGroupsResponse;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.repository.InstituteGroupsRepository;
import com.sta.settings.service.InstituteGroupsService;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.Predicate;

/**
 * Business logic for InstituteGroups will implements here. Temporarily only
 * base DRUD is implanting, rest of the logic will implements once the UX design
 * confirmed.
 * 
 * @author r@ghu
 *
 */
@Slf4j
@Service("instituteGroupsService")
public class InstituteGroupsServiceImpl implements InstituteGroupsService {

	@Autowired
	private InstituteGroupsRepository instituteGroupsRepository;

	/**
	 * Create the institute-group, currently creating one at a time.
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public InstituteGroupsResponse createInstituteGroups(InstituteGroupsRequest request) {
		if (request.getId() != null)
			throw new SettingsException(ErrorCodesConstants.METHOD_NOT_ALLOWED,
					MessageConstants.PUT_OPERATION_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED, null);

		if (instituteGroupsRepository.existsByInstituteGroupNameAndAffliationNoAndActive(
				request.getInstituteGroupName(), request.getAffliationNo(), true))
			throw new SettingsException(ErrorCodesConstants.CONFLICT, MessageConstants.INSTITUTE_GROUP_ALREADY_EXIST,
					HttpStatus.CONFLICT, null);

		try {
			InstituteGroupsResponse response = null;
			MasterInstituteGroups instituteGroups = new MasterInstituteGroups(request.getInstituteGroupName(),
					request.getAffliationNo(), CommonStatus.PENDING);
			instituteGroups = instituteGroupsRepository.save(instituteGroups);
			if (!StringUtils.isBlank(instituteGroups.getId())) {
				log.info("Institute group created.");
				response = new InstituteGroupsResponse(instituteGroups.getId(), instituteGroups.getInstituteGroupName(),
						instituteGroups.getAffliationNo());
			}
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_GROUP_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * To update
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public InstituteGroupsResponse updateInstituteGroups(InstituteGroupsRequest request) {
		if (!instituteGroupsRepository.existsById(request.getId()))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_GROUP_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		if (instituteGroupsRepository.existsByInstituteGroupNameAndAffliationNoAndActiveAndIdNot(
				request.getInstituteGroupName(), request.getAffliationNo(), true, request.getId()))
			throw new SettingsException(ErrorCodesConstants.CONFLICT, MessageConstants.INSTITUTE_GROUP_ALREADY_EXIST,
					HttpStatus.CONFLICT, null);

		try {
			Long currentTime = new Date().getTime();
			MasterInstituteGroups instituteGroups = instituteGroupsRepository.getReferenceById(request.getId());
			instituteGroups.setInstituteGroupName(request.getInstituteGroupName());
			instituteGroups.setAffliationNo(request.getAffliationNo());
			//instituteGroups.setLastModifiedAt(currentTime);
			instituteGroups = instituteGroupsRepository.save(instituteGroups);
			log.info("Updated the institute group: " + request.getInstituteGroupName());
			return new InstituteGroupsResponse(instituteGroups.getId(), instituteGroups.getInstituteGroupName(),
					instituteGroups.getAffliationNo());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_GROUP_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public InstituteGroupsResponse getInstituteGroupsById(String id) {
		if (!instituteGroupsRepository.existsById(id))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_GROUP_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			MasterInstituteGroups instituteGroups = instituteGroupsRepository.getReferenceById(id);
			log.info("Institute group details fetched by id.");
			return new InstituteGroupsResponse(instituteGroups.getId(), instituteGroups.getInstituteGroupName(),
					instituteGroups.getAffliationNo());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_GROUP_GET_BY_ID_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public List<MasterInstituteGroups> getAllInstituteGroups(String name) {
		try {
			List<Predicate> predicates = new ArrayList<>();
			Specification<MasterInstituteGroups> spec = (root, query, criteriaBuilder) -> {
				predicates.add(criteriaBuilder.equal(root.get("active"), true));
				if (name != null && !name.isEmpty())
					predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("instituteGroupName")),
							"%" + name.toLowerCase() + "%"));
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			};
			return instituteGroupsRepository.findAll(spec);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.INSTITUTE_GROUP_GET_ALL, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Update the active column into false
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Boolean deleteInstituteGroupsById(String id) {
		if (!instituteGroupsRepository.existsById(id))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_GROUP_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			Long currentTime = new Date().getTime();
			int deleteStatus = instituteGroupsRepository.setActiveStatus(currentTime, id, true);
			log.info(deleteStatus > 0 ? "Institute group deleted successfully!" : "Failed to delete institute group.");
			return deleteStatus > 0;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.INSTITUTE_GROUP_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}
}
