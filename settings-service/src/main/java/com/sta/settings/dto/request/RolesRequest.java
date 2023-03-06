package com.sta.settings.dto.request;

import javax.validation.constraints.NotBlank;

import com.sta.dc.common.utility.SettingsConstants;

import lombok.Data;

@Data
public class RolesRequest {

	/* Use it during update a role, unless try to leave it as null */
	private String id;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String roleId;
	
	@NotBlank(message = SettingsConstants.MANDATORY)
	private String instituteId;
}