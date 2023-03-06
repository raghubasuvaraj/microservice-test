package com.sta.settings.service;

import java.util.List;

import com.sta.settings.dto.request.BranchFormsRequest;
import com.sta.settings.dto.response.ByBranchOrMasterFormResponse;

import com.sta.settings.entities.BranchFormsEntity;
import com.sta.settings.exception.SettingsException;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Interface to interact with the business logic with Assign form
 * 
 * @author r@ghu
 *
 */
@Server
public interface BranchFormsService {

	/**
	 * Assigning master-form to the branch.
	 * <p>
	 * Cannot find the entity of master-form, once it got confirmed, just like
	 * branch master form will also identified as foreign key.
	 *
	 * @param request
	 * @return
	 */
	BranchFormsEntity createAssignFormToBranch(BranchFormsRequest request) throws SettingsException;

	/**
	 * Update the master-form which is assigned to a branch.
	 *
	 * @param request
	 * @return
	 */
	BranchFormsEntity updateAssignFormToBranch(BranchFormsRequest request);

	/**
	 * Unassigns or delete the master-forms from the branch.
	 * 
	 * @param branchId
	 * @return
	 */
	Boolean unAssignFormFromTheBranch(String branchId);

	/**
	 * Fetch the assigned master-form under a branch
	 *
	 * @param branchId
	 * @return
	 */
	List<ByBranchOrMasterFormResponse> getAllAssignedFormsByBranchId(String branchId);

	/**
	 * Get the branch list by master form id.
	 * <p>
	 * 
	 * @see <a href=
	 *      "https://xd.adobe.com/view/ad7e7408-892e-4573-8840-b472ac72999a-f82d/screen/83cd5646-0175-4a28-bc3a-bcd0930aa8b8">DPS
	 *      Layout - 77</a>
	 * 
	 * @return
	 */
	List<ByBranchOrMasterFormResponse> getAllFormsWithBranches(String formName);

}
