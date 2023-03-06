package com.sta.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sta.dto.response.RolesResponse;
import com.sta.entity.Roles;
import com.sta.enums.ErrorCodes;
import com.sta.exception.AuthException;
import com.sta.repository.RolesRepository;
import com.sta.service.KeycloakService;
import com.sta.service.RolesService;

import lombok.extern.slf4j.Slf4j;

/**
 * Business logic for roles
 * @author r@ghu
 *
 */
@Slf4j
@Service("rolesService")
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private KeycloakService keycloakService;
	
	/**
	 * @since 1.0.0
	 * @param roles
	 * @return
	 */
	@Override
	public List<RolesResponse> createRoles(List<String> roles) {
		log.info("Roles creation started...");
		if(CollectionUtils.isEmpty(roles)) {
			throw new AuthException(ErrorCodes.BAD_REQUEST, "Please add at least one role.");
		}
		try {
			List<RolesResponse> response = new ArrayList<>();
			roles.forEach(item->{
				if(!rolesRepository.existsByRole(item)) {
					Roles role = new Roles();
					role.setRole(item);
					role = rolesRepository.save(role);
					if(!StringUtils.isEmpty(role.getId())) {
						log.info("Role created, role is creating in the keycloak realm.");
						keycloakService.addRealmRole(role.getRole());
						response.add(new RolesResponse(role.getId(), role.getRole()));
					}
				}
			});
			log.info("Roles creation completed");
			return response;
		}catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, "Failed to create roles.");
		}
	}
	
	@Override
	public List<RolesResponse> getAllRoles() {
		log.info("Get all roles started");
		try {
			log.info("Get all roles completed.");
			return rolesRepository.findAllRoles();
		}catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, "All roles listed");
		}
	}
}
