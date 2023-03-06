package com.sta.settings.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 1. Bringing the master-forms assigned under a specific branch. OR
 * <p>
 * 2. Bring the branches by master-forms (According to the screen no: 27), go to
 * the link below.
 * <p>
 * 
 * @author r@ghu
 * 
 * @see <a href=
 *      "https://xd.adobe.com/view/ad7e7408-892e-4573-8840-b472ac72999a-f82d/screen/83cd5646-0175-4a28-bc3a-bcd0930aa8b8">DPS
 *      Layout - 77</a>
 *      <p>
 * 
 * @JsonInclude(value = Include.NON_NULL) will display to front-end if the field
 *                    has data.
 *
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ByBranchOrMasterFormResponse {

	@JsonInclude(value = Include.NON_NULL)
	private String masterFormId;
	
	@JsonInclude(value = Include.NON_NULL)
	private String masterForm;

	private List<BranchFormsResponse> assignedForms;

}
