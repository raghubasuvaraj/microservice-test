package com.sta.service;

import org.springframework.stereotype.Service;

import com.sta.dto.request.KeycloakUserRequest;

@Service
public interface KeycloakService {

	/**
	 * Create a user in the keycloak via user create API.
	 * 
	 * @param request
	 * @return
	 */
	String createUser(KeycloakUserRequest request);

	/**
	 * update the password to the keycloak
	 * 
	 * @param keycloakUserId
	 * @param password
	 */
	void updateCredentials(String keycloakUserId, String password);

	/**
	 * Add roles to the user
	 * 
	 * @param role
	 * @param keycloakUserId
	 */
	void addRole(String role, String keycloakUserId);

	/**
	 * Update user by removing
	 * 
	 * @param existingRole
	 * @param newRole
	 * @param keycloakUserId
	 */
	void updateRole(String existingRole, String newRole, String keycloakUserId);

	/**
	 * Remove roles
	 * 
	 * @param role
	 * @param keycloakUserId
	 */
	void removeRoles(String role, String keycloakUserId);

	/**
	 * Create the Role to the keycloak.
	 * http://localhost:8080/auth/admin/master/console/#/create/role/your-realm
	 * 
	 * @param role
	 */
	void addRealmRole(String role);

}
