package com.sta.service.impl;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sta.component.UserRepresentationConverter;
import com.sta.config.KeycloakConfigProperties;
import com.sta.dto.request.KeycloakUserRequest;
import com.sta.enums.ErrorCodes;
import com.sta.exception.AuthException;
import com.sta.service.KeycloakService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("keycloakService")
public class KeycloakServiceImpl implements KeycloakService {

	@Autowired
	private Keycloak keycloak;

	@Autowired
	private UserRepresentationConverter userRepresentationConverter;

	@Autowired
	private KeycloakConfigProperties keycloakConfigProperties;

	/**
	 * Create a user in the keycloak via user create API.
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public String createUser(KeycloakUserRequest request) {
		try {
			UserRepresentation userRepresentation = userRepresentationConverter.convert(request);
			addCredentials(userRepresentation, request.getPassword());
			Response response = keycloak.realm(keycloakConfigProperties.getRealm()).users().create(userRepresentation);
			log.info("Response status" + response.getStatus());

			if (response.getStatus() == 409) {
				throw new AuthException(ErrorCodes.CONFLICT, "User alrady exist in Keycloak");
			}

			String location = response.getLocation().getPath();
			String keycloakUserId = location.substring(location.lastIndexOf("/") + 1);
			if (response.getStatus() != 201) {
				removeUser(keycloakUserId);
				throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, "Failed to register user to keycloak");
			}
			response.close();
			return keycloakUserId;
		} catch (AuthException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new AuthException(e.getErrorCode(), e.getMessage());
		}
	}

	/**
	 * Adding the credentials to the keycloak user section
	 * 
	 * @param userRepresentation
	 * @param password
	 */
	private void addCredentials(UserRepresentation userRepresentation, String password) {
		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
		credentialRepresentation.setValue(password);
		userRepresentation.setCredentials(Arrays.asList(credentialRepresentation));

	}

	/**
	 * update the password to the keycloak
	 * 
	 * @param keycloakUserId
	 * @param password
	 */
	@Override
	public void updateCredentials(String keycloakUserId, String password) {
		UserResource userResource = keycloak.realm(keycloakConfigProperties.getRealm()).users().get(keycloakUserId);
		UserRepresentation user = userResource.toRepresentation();
		addCredentials(user, password);
		userResource.update(user);
	}

	/**
	 * Add roles to the user
	 * 
	 * @param role
	 * @param keycloakUserId
	 */
	@Override
	public void addRole(String role, String keycloakUserId) {
		try {
			RoleRepresentation realmRole = keycloak.realm(keycloakConfigProperties.getRealm()).roles().get(role)
					.toRepresentation();
			UserResource userResource = keycloak.realm(keycloakConfigProperties.getRealm()).users().get(keycloakUserId);
			userResource.roles().realmLevel().add(Arrays.asList(realmRole));
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, "Failed to map user-roles");
		}
	}

	/**
	 * Update user by removing
	 * 
	 * @param existingRole
	 * @param newRole
	 * @param keycloakUserId
	 */
	@Override
	public void updateRole(String existingRole, String newRole, String keycloakUserId) {

		RoleRepresentation realmRole = keycloak.realm(keycloakConfigProperties.getRealm()).roles().get(newRole)
				.toRepresentation();
		UserResource userResource = keycloak.realm(keycloakConfigProperties.getRealm()).users().get(keycloakUserId);

		RoleRepresentation rolesToRemove = keycloak.realm(keycloakConfigProperties.getRealm()).roles().get(existingRole)
				.toRepresentation();
		userResource.roles().realmLevel().remove(Arrays.asList(rolesToRemove));
		userResource.roles().realmLevel().add(Arrays.asList(realmRole));

	}

	/**
	 * Remove roles
	 * 
	 * @param role
	 * @param keycloakUserId
	 */
	@Override
	public void removeRoles(String role, String keycloakUserId) {
		UserResource userResource = keycloak.realm(keycloakConfigProperties.getRealm()).users().get(keycloakUserId);
		RoleRepresentation rolesToRemove = keycloak.realm(keycloakConfigProperties.getRealm()).roles().get(role)
				.toRepresentation();
		userResource.roles().realmLevel().remove(Arrays.asList(rolesToRemove));
	}

	/**
	 * Create the Role to the keycloak.
	 * http://localhost:8080/auth/admin/master/console/#/create/role/your-realm
	 * 
	 * @param role
	 */
	@Override
	public void addRealmRole(String role) {
		try {
			RoleRepresentation roleRepresentation = new RoleRepresentation();
			roleRepresentation.setName(role);
			keycloak.realm(keycloakConfigProperties.getRealm()).roles().create(roleRepresentation);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, "Role adding to the realm failed");
		}
	}

	/**
	 * will check this condition later
	 * 
	 * @param role
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean checkRoleExist(String role) {
		RoleRepresentation roleRepresentation = keycloak.realm(keycloakConfigProperties.getRealm()).roles().get(role)
				.toRepresentation();
		return roleRepresentation.getName().equalsIgnoreCase(role);
	}

	/**
	 * remove the user from keycloak.
	 * 
	 * @param keycloakUserId
	 * @return
	 */
	public Response removeUser(String keycloakUserId) {
		return keycloak.realm(keycloakConfigProperties.getRealm()).users().delete(keycloakUserId);
	}
}
