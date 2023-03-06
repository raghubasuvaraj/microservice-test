package com.sta.settings.controller;

import java.util.List;

import javax.validation.Valid;

import com.sta.settings.entities.BranchFormsEntity;
import com.sta.settings.exception.SettingsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sta.settings.dto.request.BranchFormsRequest;
import com.sta.settings.dto.response.ByBranchOrMasterFormResponse;
import com.sta.settings.service.BranchFormsService;

/**
 * API end-points, to handle the CRUD of BranchForms.
 * 
 * @author r@ghu
 *
 */
@RestController
@RequestMapping("/api/v1/branchForms")
public class BranchFormsController {

	@Autowired
	private BranchFormsService assignFormService;

	@PostMapping("/create")
	public ResponseEntity<BranchFormsEntity> createAssignFormToBranch(
			@RequestBody @Valid BranchFormsRequest request) throws SettingsException {
		return ResponseEntity.status(HttpStatus.CREATED).body(assignFormService.createAssignFormToBranch(request));
	}

	@PutMapping
	public ResponseEntity<BranchFormsEntity> updateAssignFormToBranch(
			@Valid @RequestBody BranchFormsRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(assignFormService.updateAssignFormToBranch(request));
	}

	@DeleteMapping("/by-branch/{branchId}")
	public ResponseEntity<Boolean> unAssignFormFromTheBranch(@PathVariable("branchId") String branchId) {
		Boolean response = assignFormService.unAssignFormFromTheBranch(branchId);
		return new ResponseEntity<>(response,
				(Boolean.TRUE.equals(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{branchId}")
	public ResponseEntity<List<ByBranchOrMasterFormResponse>> getAllAssignedFormsByBranchId(
			@PathVariable("branchId") String branchId) {
		return ResponseEntity.status(HttpStatus.OK).body(assignFormService.getAllAssignedFormsByBranchId(branchId));
	}

	@GetMapping("/by-master-form")
	public ResponseEntity<List<ByBranchOrMasterFormResponse>> getAllFormsWithBranches(@RequestParam(value = "formName", required = false) String formName) {
		return ResponseEntity.status(HttpStatus.OK).body(assignFormService.getAllFormsWithBranches(formName));
	}
}
