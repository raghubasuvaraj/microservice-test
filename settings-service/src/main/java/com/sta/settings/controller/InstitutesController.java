package com.sta.settings.controller;

import java.util.List;

import javax.validation.Valid;

import com.sta.settings.service.IInstitutesService;
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
import org.springframework.web.bind.annotation.RestController;

import com.sta.settings.dto.request.ActiveStatusToggleRequest;
import com.sta.settings.dto.request.InstitutesApproveRequest;
import com.sta.settings.dto.request.InstitutesRequest;
import com.sta.settings.dto.response.InstitutesResponse;

@RestController
@RequestMapping("/api/v1/institutes")
public class InstitutesController {

	@Autowired
	private IInstitutesService institutesService;

	@PostMapping
	public ResponseEntity<InstitutesResponse> createInstitutes(@Valid @RequestBody InstitutesRequest request) {
		InstitutesResponse response = institutesService.createInstitute(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping
	public ResponseEntity<InstitutesResponse> updateInstitutes(@Valid @RequestBody InstitutesRequest request) {
		InstitutesResponse response = institutesService.updateInstitute(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InstitutesResponse> getInstituteById(@PathVariable("id") String id) {
		InstitutesResponse response = institutesService.getInstituteById(id);
		return new ResponseEntity<>(response, (!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<List<InstitutesResponse>> getAllInstitutes() {
		List<InstitutesResponse> response = institutesService.getAllInstitute();
		return new ResponseEntity<>(response,
				(!CollectionUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@PutMapping("/toggle-active")
	public ResponseEntity<Boolean> toggleActiveStatus(@Valid @RequestBody ActiveStatusToggleRequest request) {
		Boolean response = institutesService.toggleActiveStatus(request);
		return new ResponseEntity<>(response,
				(Boolean.TRUE.equals(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteInstitute(@PathVariable("id") String id) {
		Boolean response = institutesService.deleteInstitute(id);
		return new ResponseEntity<>(response,
				(Boolean.TRUE.equals(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/approve-or-reject")
	public ResponseEntity<InstitutesResponse> approveOrRejectInstitute(
			@Valid @RequestBody InstitutesApproveRequest request) {
		InstitutesResponse response = institutesService.approveOrRejectInstitute(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

}