package com.sta.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sta.dto.request.KeycloakUserRequest;
import com.sta.dto.request.UsersRequest;
import com.sta.dto.response.UsersResponse;
import com.sta.dto.response.UsersRoleResponse;
import com.sta.entity.Roles;
import com.sta.entity.UserRolesMapping;
import com.sta.entity.Users;
import com.sta.enums.ErrorCodes;
import com.sta.exception.AuthException;
import com.sta.repository.RolesRepository;
import com.sta.repository.UserRolesMappingRepository;
import com.sta.repository.UsersRepository;
import com.sta.service.KeycloakService;
import com.sta.service.UsersService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author r@ghu
 *
 */
@Slf4j
@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private UserRolesMappingRepository urMappingRepository;

	@Autowired
	private KeycloakService keycloakService;

	/**
	 * Create user by administrator
	 * 
	 * @param request
	 * @return response
	 */
	@Override
	@Transactional
	@SuppressWarnings("all")
	public UsersResponse createUser(UsersRequest request) {
		try {
			UsersResponse response = null;
			String currentUser = securityUtil.getCurrentUser();

			Users users = new Users();
			users.setName(request.getName());
			users.setMobile(request.getMobile());
			users.setEmail(request.getEmail());
			users = usersRepository.save(users);

			if (!StringUtils.isEmpty(users.getId())) {
				log.info("Mapping user to keyclok");

				KeycloakUserRequest keycloakDto = new KeycloakUserRequest(users.getId(), users.getEmail(),
						users.getEmail(), users.getName(), null, request.getPassword());

				String keycloakUserId = keycloakService.createUser(keycloakDto);
				users.setKeycloakUserId(keycloakUserId);
				usersRepository.save(users);

				log.info("User registered, user role mapping started...");
				for (String roleId : request.getRoles()) {
					Roles roles = rolesRepository.getReferenceById(roleId);
					UserRolesMapping urMapping = new UserRolesMapping();
					urMapping.setUsers(users);
					urMapping.setRoles(roles);
					urMappingRepository.save(urMapping);

					keycloakService.addRole(roles.getRole(), keycloakUserId);
				}

				List<UsersRoleResponse> innerResponse = urMappingRepository.findRolesByUserId(users.getId());
				response = new UsersResponse(users.getId(), users.getName(), users.getMobile(), users.getEmail(),
						innerResponse);
			}
			return response;
		} catch (Exception ex) {
			log.error(ExceptionUtils.getStackTrace(ex));
			throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, "Failed to create user");
		}
	}

	/**
	 * Example method to show only privileged user can access a specific API.
	 * <p>
	 * Here the privileged role is SUPER_ADMIN
	 * 
	 * @return
	 */
	@Override
	public List<UsersResponse> getAllUsersForSuperAdmin() {
		try {
			log.info("Listing all the users to super admin.");
			return usersRepository.findAllUsersToSuperAdmin();
		} catch (Exception ex) {
			log.error(ExceptionUtils.getStackTrace(ex));
			throw new AuthException(ErrorCodes.INTERNAL_SERVER_ERROR, "Failed to get all users to super admin");
		}
	}
}
