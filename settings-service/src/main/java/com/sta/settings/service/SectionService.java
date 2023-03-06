package com.sta.settings.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sta.settings.dto.request.SectionDeleteRequest;
import com.sta.settings.dto.request.SectionRequest;
import com.sta.settings.dto.response.SectionWithClassResponse;

/**
 * Service for Section's CRUD
 * @author r@ghu
 *
 */
@Service
public interface SectionService {

	/**
	 * Create a section either under a Class or Curriculum. Branch took as mandatory
	 * (according to UI design, will change it, if it is no necessary).
	 * 
	 * @param request
	 * @return
	 */
	SectionWithClassResponse createSections(SectionRequest request);

	/**
	 * Update the section if it has id or else will check it is already exist with
	 * the branch id, class id and active=true. If not exist will create a new
	 * section.
	 *
	 * @param request
	 * @return
	 */
	SectionWithClassResponse updateSections(SectionRequest request);

	/**
	 * With the help of a specific class id bring the sections
	 *
	 * @param classId
	 * @return
	 */
	SectionWithClassResponse getAllSectionBySpecificClass(String classId);

	/**
	 * Get all the classes and assigned sections to each particular class.
	 *
	 * @return
	 */
	List<SectionWithClassResponse> getAllClassWithSections();

	/**
	 * Set the active=false, active column consider as delete status
	 *
	 * @param sections
	 * @return
	 */
	Boolean deleteSections(SectionDeleteRequest request);
}
