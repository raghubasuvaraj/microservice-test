package com.sta.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersRoleResponse {
	
	/**
	 * primary key of user-role-mapping table
	 */
	private String id;
	
	private String role;
}
