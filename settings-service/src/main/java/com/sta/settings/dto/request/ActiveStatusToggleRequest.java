package com.sta.settings.dto.request;

import javax.validation.constraints.NotBlank;

import com.sta.dc.common.utility.SettingsConstants;

import lombok.Data;

@Data
public class ActiveStatusToggleRequest {
	
	@NotBlank(message = SettingsConstants.MANDATORY)
	private String id;
	
	private boolean active;
}
