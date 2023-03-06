package com.sta.settings.service;

import java.util.List;
import java.util.Set;

import com.sta.settings.entities.RolesEntity;
import org.springframework.stereotype.Service;

import com.sta.settings.dto.request.ActiveStatusToggleRequest;
import com.sta.settings.dto.request.RolesRequest;
import com.sta.settings.dto.response.RolesResponse;

@Service
public interface IRolesService {

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
	Set<RolesResponse> createOrUpdateRoles(Set<RolesRequest> requests);

	/**
	 * Individual role update
	 * @param request
	 * @return
	 */
	RolesResponse updateRole(RolesRequest request);

	RolesResponse getRoleById(String id);

	List<RolesEntity> getAllRoles(String instituteId);

	Boolean toggleActiveStatus(ActiveStatusToggleRequest request);

	Boolean deleteRole(String id);
}
