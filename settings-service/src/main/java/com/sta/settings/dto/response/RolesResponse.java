package com.sta.settings.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RolesResponse {
	
	private String id;
	
	private String role;
	
	private boolean active;
}
