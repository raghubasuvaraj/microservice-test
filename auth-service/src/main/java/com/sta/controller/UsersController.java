package com.sta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sta.dto.request.UsersRequest;
import com.sta.dto.response.UsersResponse;
import com.sta.service.UsersService;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@PostMapping()
	public ResponseEntity<UsersResponse> createUser(@Valid @RequestBody UsersRequest request) {
		UsersResponse response = usersService.createUser(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasAuthority('ROLE_SUPER-ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<UsersResponse>> getAllUsersForSuperAdmin() {
		List<UsersResponse> response = usersService.getAllUsersForSuperAdmin();
		return new ResponseEntity<>(response,
				(!CollectionUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
}
