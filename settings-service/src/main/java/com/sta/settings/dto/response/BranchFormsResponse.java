package com.sta.settings.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Get the response of AssignFormEntity.
 * <p>
 * 
 * @JsonInclude(value = Include.NON_NULL) will display to front-end if the field
 *                    has data.
 * @author r@ghu
 *
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BranchFormsResponse {

	/**
	 * primary key of the AssignFormEntity
	 */
	@JsonInclude(value = Include.NON_NULL)
	private String branchId;

	@JsonInclude(value = Include.NON_NULL)
	private String branch;

	@JsonInclude(value = Include.NON_NULL)
	private String masterFormUrl;

}
