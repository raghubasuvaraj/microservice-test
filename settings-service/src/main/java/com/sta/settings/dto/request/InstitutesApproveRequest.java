package com.sta.settings.dto.request;

import javax.validation.constraints.NotBlank;

import com.sta.dc.common.utility.SettingsConstants;

import lombok.Data;

@Data
public class InstitutesApproveRequest {
	
	@NotBlank(message = SettingsConstants.MANDATORY)
	private String id;
	
	@NotBlank(message = SettingsConstants.MANDATORY)
	private String approvedStatus;
	
	private String comments;
	
}
