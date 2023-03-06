package com.sta.settings.service;

import java.util.List;

import com.sta.settings.dto.request.MasterFormsRequest;
import com.sta.settings.dto.response.MasterFormsResponse;

import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Declaration of the master-forms CRUD operation
 * @author r@ghu
 *
 */
@Server
public interface MasterFormsService {

	/**
	 * Creating a master form under a curriculum. Screen is not sure so going with
	 * one at a time.
	 * 
	 * @param request
	 * @return
	 */
//	MasterFormsResponse createMasterForms(MasterFormsRequest request);

	/**
	 * Update master-form by id
	 * 
	 * @param request
	 * @return
	 */
//	MasterFormsResponse updateMasterForms(MasterFormsRequest request);
	
	/**
	 * Getting all the master-form by curriculum
	 * 
	 * @param carriculumId
	 * @return
	 */
//	List<MasterFormsResponse> getMasterFormByCarriculumn(String carriculumId);

}
