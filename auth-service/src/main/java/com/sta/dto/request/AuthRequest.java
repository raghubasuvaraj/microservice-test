package com.sta.dto.request;

import javax.validation.constraints.NotBlank;

import com.sta.utilities.AuthConstants;

import lombok.Data;

/**
 * Request for login
 * @author r@ghu
 *
 */
@Data
public class AuthRequest {
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String username;
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String password;
}
