package com.sta.settings.dto.request;

import javax.validation.constraints.NotBlank;

import com.sta.dc.common.utility.SettingsConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for persisting layer master form
 * 
 * @author r@ghu
 *
 */
@Getter
@Setter
public class MasterFormsRequest {

	/**
	 * Expecting PK of master-forms entity. Do not use it during the POST API
	 */
	private String id;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String name;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String shortCode;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String curriculumId;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String status;
}
