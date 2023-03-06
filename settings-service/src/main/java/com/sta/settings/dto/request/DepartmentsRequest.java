package com.sta.settings.dto.request;

import javax.validation.constraints.NotBlank;

import com.sta.dc.common.utility.SettingsConstants;

import lombok.Data;

/**
 * Will implements the value specific validation once the value's format is
 * confirmed.
 * 
 * @author r@ghu | Thur 28th July 2022
 *
 */
@Data
public class DepartmentsRequest {
	
	//Insert value during the update of department.
	private String id;

	private String masterDepartmentId;

	private String name;

	private String code;
}
