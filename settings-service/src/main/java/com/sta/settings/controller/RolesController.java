package com.sta.settings.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.sta.settings.entities.RolesEntity;
import com.sta.settings.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.sta.settings.dto.request.ActiveStatusToggleRequest;
import com.sta.settings.dto.request.RolesRequest;
import com.sta.settings.dto.response.RolesResponse;

/**
 * CRUD API for the roles
 * 
 * <p>
 * Response body/structure will be implements once the common-utilities
 * implemented as a dependency.
 * 
 * @author r@ghu
 *
 */
@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {

	@Autowired
	private IRolesService rolesService;

	@PostMapping
	public ResponseEntity<Set<RolesResponse>> createOrUpdateRoles(@Valid @RequestBody Set<RolesRequest> requests) {
		Set<RolesResponse> response = rolesService.createOrUpdateRoles(requests);
		return new ResponseEntity<>(response,
				(!CollectionUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping
	public ResponseEntity<RolesResponse> updateRole(@Valid @RequestBody RolesRequest request) {
		RolesResponse response = rolesService.updateRole(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RolesResponse> getRoleById(@PathVariable("id") String id) {
		RolesResponse response = rolesService.getRoleById(id);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping
	public ResponseEntity<List<RolesEntity>> getAllRoles(
			@RequestParam(value = "instituteId", required = false)String instituteId
	) {
		List<RolesEntity> response = rolesService.getAllRoles(instituteId);
		return new ResponseEntity<>(response,
				(!CollectionUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@PutMapping("/toggle-active")
	public ResponseEntity<Boolean> toggleActiveStatus(@Valid @RequestBody ActiveStatusToggleRequest request) {
		Boolean response = rolesService.toggleActiveStatus(request);
		return new ResponseEntity<>(response,
				(Boolean.TRUE.equals(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteRole(@PathVariable("id") String id) {
		Boolean response = rolesService.deleteRole(id);
		return new ResponseEntity<>(response,
				(Boolean.TRUE.equals(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
