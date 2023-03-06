package com.sta.settings.controller;

import java.util.List;

import javax.validation.Valid;

import com.sta.settings.entities.MasterInstituteGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.sta.settings.dto.request.InstituteGroupsRequest;
import com.sta.settings.dto.response.InstituteGroupsResponse;
import com.sta.settings.service.InstituteGroupsService;

/**
 * API end-point to access the institute-groups
 * @author r@ghu
 *
 */
@RestController
@RequestMapping("/api/v1/institute-groups")
public class InstituteGroupsController {

	@Autowired
	private InstituteGroupsService instituteGroupsService;

	@PostMapping
	public ResponseEntity<InstituteGroupsResponse> createInstituteGroups(
			@Valid @RequestBody InstituteGroupsRequest request) {
		InstituteGroupsResponse response = instituteGroupsService.createInstituteGroups(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping
	public ResponseEntity<InstituteGroupsResponse> updateInstituteGroups(
			@Valid @RequestBody InstituteGroupsRequest request) {
		InstituteGroupsResponse response = instituteGroupsService.updateInstituteGroups(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InstituteGroupsResponse> getInstituteGroupsById(@PathVariable("id") String id) {
		InstituteGroupsResponse response = instituteGroupsService.getInstituteGroupsById(id);
		return new ResponseEntity<>(response, (!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<List<MasterInstituteGroups>> getAllInstituteGroups(
			@RequestParam(value = "name", required = false)String name) {
		List<MasterInstituteGroups> response = instituteGroupsService.getAllInstituteGroups(name);
		return new ResponseEntity<>(response,
				(!CollectionUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteInstituteGroupsById(@PathVariable("id") String id) {
		Boolean response = instituteGroupsService.deleteInstituteGroupsById(id);
		return new ResponseEntity<>(response,
				(Boolean.TRUE.equals(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
