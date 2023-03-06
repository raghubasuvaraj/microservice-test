package com.sta.settings.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.sta.settings.dto.request.DepartmentRequestList;
import com.sta.settings.entities.InstituteDepartmentsEntity;
import com.sta.settings.service.IDepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sta.settings.dto.request.ActiveStatusToggleRequest;
import com.sta.settings.dto.request.DepartmentsRequest;
import com.sta.settings.dto.response.DepartmentsResponse;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentsController {

	@Autowired
	private IDepartmentsService departmentsService;

	@PostMapping
	public ResponseEntity<List<InstituteDepartmentsEntity>> createDepartmentsForInstitute(
			@Valid @RequestBody DepartmentRequestList request,
			@RequestParam(value = "instituteId", required = true) String instituteId) {
		List<InstituteDepartmentsEntity> response = departmentsService.createDepartments(request,instituteId);
		return new ResponseEntity<>(response,
				(!CollectionUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping
	public ResponseEntity<DepartmentsResponse> updateDepartment(@Valid @RequestBody DepartmentsRequest request) {
		DepartmentsResponse response = departmentsService.updateDepartment(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InstituteDepartmentsEntity> getDepartmentById(@PathVariable("id") String id) {
		InstituteDepartmentsEntity response = departmentsService.getDepartmentById(id);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/departments")
	public ResponseEntity<List<InstituteDepartmentsEntity>> getAllDepartmentss(@RequestParam(value = "instituteId", required = false) String instituteId,
																			 @RequestParam(value = "departmentName", required = false) String departmentName) {
		return ResponseEntity.ok().body(departmentsService.getAllDepartments(instituteId, departmentName));
	}

	@PutMapping("/toggle-active")
	public ResponseEntity<Object> toggleActiveStatus(@Valid @RequestBody ActiveStatusToggleRequest request) {
		Boolean response = departmentsService.toggleActiveStatus(request);
		return new ResponseEntity<>(response,
				(Boolean.TRUE.equals(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDepartmentById(@PathVariable("id") String id) {
		Boolean response = departmentsService.deleteDepartmentById(id);
		return new ResponseEntity<>(response,
				(Boolean.TRUE.equals(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
