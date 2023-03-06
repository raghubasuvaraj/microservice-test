package com.sta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sta.dto.request.UsersRequest;
import com.sta.dto.response.UsersResponse;

/**
 * @author r@ghu
 *
 */
@Service
public interface UsersService {

	/**
	 * Create user by administrator
	 * 
	 * @param request
	 * @return response
	 */
	UsersResponse createUser(UsersRequest request);

	/**
	 * Example method to show only privileged user can access a specific API.
	 * <p>
	 * Here the privileged role is SUPER_ADMIN
	 * 
	 * @return
	 */
	List<UsersResponse> getAllUsersForSuperAdmin();

}
