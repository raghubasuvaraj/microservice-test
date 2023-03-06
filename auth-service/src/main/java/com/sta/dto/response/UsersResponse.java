package com.sta.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author r@ghu
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {

	private String id;

	private String name;

	private String mobile;

	private String email;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<UsersRoleResponse> roles;
	
	public UsersResponse(String id, String name, String mobile, String email) {
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
	}

}