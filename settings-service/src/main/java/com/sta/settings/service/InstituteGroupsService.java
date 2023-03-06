package com.sta.settings.service;

import java.util.List;

import com.sta.settings.entities.MasterInstituteGroups;
import org.springframework.stereotype.Service;

import com.sta.settings.dto.request.InstituteGroupsRequest;
import com.sta.settings.dto.response.InstituteGroupsResponse;

/**
 * API services will be declare here for InstituteGroups.
 * @author r@ghu
 *
 */
@Service
public interface InstituteGroupsService {

	/**
	 * Create the institute-group, currently creating one at a time.
	 * @param request
	 * @return
	 */
	InstituteGroupsResponse createInstituteGroups(InstituteGroupsRequest request);

	/**
	 * To update
	 * @param request
	 * @return
	 */
	InstituteGroupsResponse updateInstituteGroups(InstituteGroupsRequest request);

	InstituteGroupsResponse getInstituteGroupsById(String id);

	List<MasterInstituteGroups> getAllInstituteGroups(String name);

	/**
	 * Update the active column into false
	 * 
	 * @param id
	 * @return
	 */
	Boolean deleteInstituteGroupsById(String id);

}
