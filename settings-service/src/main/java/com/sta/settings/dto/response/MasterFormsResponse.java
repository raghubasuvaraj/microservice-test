package com.sta.settings.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sta.settings.enums.ApprovedStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * To show the details of the master-forms
 * 
 * @author r@ghu
 *
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class MasterFormsResponse {

	private String id;

	private String name;

	private String shortCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String curriculumId;
	
	@JsonInclude(value = Include.NON_NULL)
	private String curriculum;

	private ApprovedStatus status;
	
	public MasterFormsResponse(String id, String name, String shortCode, ApprovedStatus status) {
		this.id = id;
		this.name = name;
		this.shortCode = shortCode;
		this.status = status;
	}
	
}
