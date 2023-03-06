package com.sta.settings.service;

import java.util.List;
import java.util.Set;

import com.sta.settings.dto.request.DepartmentRequestList;
import com.sta.settings.entities.InstituteDepartmentsEntity;
import org.springframework.stereotype.Service;

import com.sta.settings.dto.request.ActiveStatusToggleRequest;
import com.sta.settings.dto.request.DepartmentsRequest;
import com.sta.settings.dto.response.DepartmentsResponse;

@Service
public interface IDepartmentsService {

	/**
	 * Checking the duplication, then saving each department by the branch
	 * 
	 * @param request
	 * @return
	 */
	List<InstituteDepartmentsEntity> createDepartments(DepartmentRequestList request, String instituteId);

	/**
	 * Update the department
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	DepartmentsResponse updateDepartment(DepartmentsRequest request);

	/**
	 * Get by id [specific department]
	 * @param id
	 * @return
	 */
	InstituteDepartmentsEntity getDepartmentById(String id);


	Boolean toggleActiveStatus(ActiveStatusToggleRequest request);

	Boolean deleteDepartmentById(String id);

	List<InstituteDepartmentsEntity> getAllDepartments(String instituteId, String departmentName);
}
