package com.sta.settings.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.sta.settings.entities.InstitutesEntity;
import com.sta.settings.entities.MasterRoleEntity;
import com.sta.settings.entities.MasterTermEntity;
import com.sta.settings.entities.RolesEntity;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.repository.MasterRoleRepository;
import com.sta.settings.service.IRolesService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sta.settings.dto.request.ActiveStatusToggleRequest;
import com.sta.settings.dto.request.RolesRequest;
import com.sta.settings.dto.response.RolesResponse;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.repository.InstitutesRepository;
import com.sta.settings.repository.RolesRepository;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.Predicate;

/**
 * Crud operation of the role types
 * <p>
 * create, update, get by id, get all, update active status, delete the role
 * type.
 * <p>
 * Will implement the role type pagination after the confirmation.
 * 
 * @author r@ghu
 *
 */
@Slf4j
@Service
public class RolesServiceImpl implements IRolesService {

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private InstitutesRepository institutesRepository;

	@Autowired
	private MasterRoleRepository masterRoleRepository;
	/**
	 * Create or update the roles
	 * <p>
	 * Checking the duplication in the request as well as in the database too.
	 * <p>
	 * If the request has id it'll check whether exist or not then update the role.
	 * <p>
	 * if request has no id then create a role.
	 * <p>
	 * modified_by & create_by will added once authentication complete.
	 * 
	 * @param requests
	 * @return
	 */
	@Override
	@Transactional
	public Set<RolesResponse> createOrUpdateRoles(Set<RolesRequest> requests) {
		log.info("Roles creating...");

		if (!checkingRolesDuplication(requests))
			throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.ROLE_DUPLICATE,
					HttpStatus.FOUND, null);

		if (!checkingRolesExistance(requests))
			throw new SettingsException(ErrorCodesConstants.CONFLICT, MessageConstants.ROLE_ALREADY_EXIST,
					HttpStatus.CONFLICT, null);

		try {
			Long currentTime = new Date().getTime();
			Set<RolesResponse> response = new LinkedHashSet<>();
			for (RolesRequest request : requests) {
				if (!StringUtils.isEmpty(request.getId())) {
					log.info("Updating the role.");
					RolesEntity rolesEntity = rolesRepository.getReferenceById(request.getId());
					if (institutesRepository.existsById(request.getInstituteId())) {
						InstitutesEntity institutesEntity = institutesRepository.getReferenceById(request.getInstituteId());
						rolesEntity.setInstitutesEntity(institutesEntity);
					} else
						throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
								HttpStatus.NOT_FOUND, null);

					if (masterRoleRepository.existsById(request.getRoleId())) {
						MasterRoleEntity masterRoleEntity = masterRoleRepository.getReferenceById(request.getRoleId());
						rolesEntity.setRole(masterRoleEntity);
					} else
						throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.ROLE_NOT_FOUND,
								HttpStatus.NOT_FOUND, null);

					rolesEntity.setLastModifiedAt(LocalDateTime.now());
					rolesEntity = rolesRepository.save(rolesEntity);
					response.add(new RolesResponse(rolesEntity.getId(), rolesEntity.getRole().getName(), rolesEntity.isActive()));
				} else {
					log.info("Create the role.");
					RolesEntity rolesEntity = new RolesEntity();
					if (institutesRepository.existsById(request.getInstituteId())) {
						InstitutesEntity institutesEntity = institutesRepository.getReferenceById(request.getInstituteId());
						rolesEntity.setInstitutesEntity(institutesEntity);
					} else
						throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
								HttpStatus.NOT_FOUND, null);

					if (masterRoleRepository.existsById(request.getRoleId())) {
						MasterRoleEntity masterRoleEntity = masterRoleRepository.getReferenceById(request.getRoleId());
						rolesEntity.setRole(masterRoleEntity);
					} else
						throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.ROLE_NOT_FOUND,
								HttpStatus.NOT_FOUND, null);
					//rolesEntity.setCreatedAt(currentTime);
					rolesEntity = rolesRepository.save(rolesEntity);
					if (!StringUtils.isBlank(rolesEntity.getId()))
						response.add(new RolesResponse(rolesEntity.getId(), rolesEntity.getRole().getName(), rolesEntity.isActive()));
				}
			}
			if (!CollectionUtils.isEmpty(response))
				log.info("Roles created and response generated.");

			return response;
		} catch (SettingsException e) {
			throw new SettingsException(e.getErrorCode(), e.getMessage(), e.getStatusCode(), e.getError());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.ROLES_CREATE_FAILED,
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Checking roles already exist in the system.
	 * 
	 * @param requests
	 * @return
	 */
	private boolean checkingRolesExistance(Set<RolesRequest> requests) {
		try {
			boolean allPassed = false;
			if (!CollectionUtils.isEmpty(requests)) {
				for (RolesRequest request : requests) {
					if (!StringUtils.isBlank(request.getId())) {
						if (!rolesRepository.existsByRoleIdAndInstitutesEntityIdAndIdNot(request.getRoleId(), request.getInstituteId(), request.getId()))
							allPassed = true;
						else
							break;
					} else {
						if (!rolesRepository.existsByRoleIdAndInstitutesEntityId(request.getRoleId(), request.getInstituteId()))
							allPassed = true;
						else
							break;
					}
				}
			}
			return allPassed;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.ROLE_EXIST_CHECKING,
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Comparing the size of the request and the roles added in the Set<>
	 * 
	 * @param requests
	 * @return
	 */
	private boolean checkingRolesDuplication(Set<RolesRequest> requests) {
		try {
			boolean allPassed = false;
			int requestCount = requests.size();
			if (!CollectionUtils.isEmpty(requests)) {
				Set<String> roles = requests.stream().map(RolesRequest::getRoleId).collect(Collectors.toSet());
				if (requestCount == roles.size())
					allPassed = true;
			}
			return allPassed;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.ROLE_DUPLICATE_CHEKING, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Individual role update
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public RolesResponse updateRole(RolesRequest request) {
		if (StringUtils.isBlank(request.getId()) || !rolesRepository.existsById(request.getId()))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.ROLE_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		if (rolesRepository.existsByRoleIdAndIdNot(request.getRoleId(), request.getId()))
			throw new SettingsException(ErrorCodesConstants.CONFLICT, MessageConstants.ROLE_ALREADY_EXIST,
					HttpStatus.CONFLICT, null);

		try {
			Long currentTime = new Date().getTime();
			log.info("Updating the role.");
			RolesEntity rolesEntity = rolesRepository.getReferenceById(request.getId());

			if (masterRoleRepository.existsById(request.getRoleId())) {
				MasterRoleEntity masterRoleEntity = masterRoleRepository.getReferenceById(request.getRoleId());
				rolesEntity.setRole(masterRoleEntity);
			} else
				throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.ROLE_NOT_FOUND,
						HttpStatus.NOT_FOUND, null);
			if (institutesRepository.existsById(request.getInstituteId())) {
				InstitutesEntity institutesEntity = institutesRepository.getReferenceById(request.getInstituteId());
				rolesEntity.setInstitutesEntity(institutesEntity);
			} else
				throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
						HttpStatus.NOT_FOUND, null);
			//rolesEntity.setLastModifiedAt(currentTime);
			rolesEntity = rolesRepository.save(rolesEntity);
			return new RolesResponse(rolesEntity.getId(), rolesEntity.getRole().getName(), rolesEntity.isActive());
		} catch (SettingsException e) {
			throw new SettingsException(e.getErrorCode(), e.getMessage(), e.getStatusCode(), e.getError());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.ROLE_UPDATE_FAILED,
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public RolesResponse getRoleById(String id) {
		log.info("Role geting by id...");
		if (StringUtils.isBlank(id) || !rolesRepository.existsById(id))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.ROLE_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			log.info("Get role by id.");
			RolesEntity rolesEntity = rolesRepository.getReferenceById(id);
			return new RolesResponse(rolesEntity.getId(), rolesEntity.getRole().getName(), rolesEntity.isActive());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.ROLE_GET_BY_ID_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public List<RolesEntity> getAllRoles(String instituteId) {
		try {
			List<Predicate> predicates = new ArrayList<>();
			Specification<RolesEntity> spec = (root, query, criteriaBuilder) -> {
				predicates.add(criteriaBuilder.equal(root.get("active"), true));
				if (instituteId != null && !instituteId.isEmpty())
					predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("institutesEntity").get("id")),
							"%" + instituteId.toLowerCase() + "%"));
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			};
			return rolesRepository.findAll(spec);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.LANGUAGES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public Boolean toggleActiveStatus(ActiveStatusToggleRequest request) {
		if (StringUtils.isBlank(request.getId()) || !rolesRepository.existsById(request.getId()))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.ROLE_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			Long currentTime = new Date().getTime();
			log.info("togle the active status of the role.");
			String status = request.isActive() ? "Active" : "De-Active";
			boolean response = false;
			RolesEntity rolesEntity = rolesRepository.getReferenceById(request.getId());
			rolesEntity.setActive(request.isActive());
			//rolesEntity.setLastModifiedAt(currentTime);
			rolesEntity = rolesRepository.save(rolesEntity);
			response = rolesEntity.isActive() == request.isActive();
			if (response)
				log.info("Role status converted to " + status);
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.ROLE_STATUS_FAILED,
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public Boolean deleteRole(String id) {
		if (StringUtils.isBlank(id) || !rolesRepository.existsById(id))
			throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.ROLE_NOT_FOUND,
					HttpStatus.NOT_FOUND, null);

		try {
			Long currentTime = new Date().getTime();
			log.info("Delete the role.");
			RolesEntity rolesEntity = rolesRepository.getReferenceById(id);
			rolesEntity.setActive(false);
			rolesEntity.setStatus(CommonStatus.DELETE);
			rolesEntity.setDeletedAt(LocalDateTime.now());
			rolesEntity = rolesRepository.save(rolesEntity);
			if (Boolean.FALSE.equals(rolesEntity.isActive()))
				log.info("Roles deleted.");
			return !rolesEntity.isActive();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.ROLE_DELETE_FAILED,
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}
}
