package com.sta.settings.dto.request;

import javax.validation.constraints.NotBlank;

import com.sta.dc.common.utility.SettingsConstants;

import lombok.Data;

/**
 * Request json for the InstituteGroups
 * 
 * @author r@ghu
 *
 */
@Data
public class InstituteGroupsRequest {

	/**
	 * Use this field during PUT API, otherwise leave it.
	 */
	private String id;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String instituteGroupName;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String affliationNo;
}
