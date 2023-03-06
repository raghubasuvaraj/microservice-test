package com.sta.settings.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Just to get the master_form id and url from the entity
 * AssignFormToBranchEntity
 * 
 * @author r@ghu
 *
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class MasterFormsFromBranchResponse {

	private String masterFormId;

	private String masterFormUrl;
}
