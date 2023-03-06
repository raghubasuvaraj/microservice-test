package com.sta.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sta.utilities.AuthConstants;

import lombok.Data;

/**
 * @author r@ghu
 *
 */
@Data
public class UsersRequest {
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String name;
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String mobile;
	
	@NotBlank(message = AuthConstants.MANDATORY)
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank(message = AuthConstants.MANDATORY)
	private String password;
	
	@NotNull(message = AuthConstants.MANDATORY)
	private String[] roles;
}