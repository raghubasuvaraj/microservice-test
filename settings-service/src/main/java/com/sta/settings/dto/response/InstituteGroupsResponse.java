package com.sta.settings.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response JSON for InstituteGroups
 * <p>
 * 
 * @JsonInclude(value = Include.NON_NULL) only include if the field has any
 *                    data.
 * 
 * @author r@ghu
 *
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class InstituteGroupsResponse {

	@JsonInclude(value = Include.NON_NULL)
	private String id;

	@JsonInclude(value = Include.NON_NULL)
	private String instituteGroupName;

	@JsonInclude(value = Include.NON_NULL)
	private String affliationNo;
}
