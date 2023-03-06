package com.sta.settings.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sta.dc.common.utility.SettingsConstants;

import lombok.Data;

/**
 * Request for assign master forms to the branch. At a time only one form is
 * accepting now
 * 
 * @author r@ghu
 *
 */
@Data
public class BranchFormsRequest {

	/**
	 * Use if only for update the assign master-form to branch
	 */
	private String id;

	private String branchId;

	private String masterFormId;

	private String name;

	private String code;

	private String masterFormUrl;

}
