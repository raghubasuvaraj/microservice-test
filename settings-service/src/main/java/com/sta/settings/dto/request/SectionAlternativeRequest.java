package com.sta.settings.dto.request;

import javax.validation.constraints.NotBlank;

import com.sta.dc.common.utility.SettingsConstants;

import lombok.Data;

@Data
public class SectionAlternativeRequest {
	
	/**
	 * Use it only during the put api. Do not use it along with Post API
	 */
	private String id;
	
	@NotBlank(message = SettingsConstants.MASTER_FIELDS_SAVED)
	private String name;

	private String shortCode;
}
