package com.sta.dto.request;

import javax.validation.constraints.NotBlank;

import com.sta.utilities.AuthConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * To register users to keycloak
 * 
 * @author r@ghu
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakUserRequest {
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String id;
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String userName;
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String email;
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String firstName;
	
	private String userEnabled;
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String password;
}
