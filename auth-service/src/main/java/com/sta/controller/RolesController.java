package com.sta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sta.dto.response.RolesResponse;
import com.sta.service.RolesService;

/**
 * API end points to do basic roles operation. This controller will be perish
 * once the demo completed.
 * 
 * @author r@ghu
 *
 */
@RestController
@RequestMapping(value = "/api/v1/roles")
public class RolesController {

	@Autowired
	private RolesService rolesService;

	@PostMapping()
	public ResponseEntity<List<RolesResponse>> createRoles(@RequestBody List<String> roles) {
		List<RolesResponse> response = rolesService.createRoles(roles);
		return new ResponseEntity<>(response,
				(!CollectionUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.NO_CONTENT);
	}

	@GetMapping()
	public ResponseEntity<List<RolesResponse>> getAllRoles() {
		List<RolesResponse> response = rolesService.getAllRoles();
		return new ResponseEntity<>(response,
				(!CollectionUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
}
