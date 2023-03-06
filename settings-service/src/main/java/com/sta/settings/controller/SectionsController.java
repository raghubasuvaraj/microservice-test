package com.sta.settings.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.sta.settings.dto.request.SectionDeleteRequest;
import com.sta.settings.dto.request.SectionRequest;
import com.sta.settings.dto.response.SectionWithClassResponse;
import com.sta.settings.service.SectionService;

/**
 * Controller for section management.
 * 
 * @author r@ghu
 *
 */
@RestController
@RequestMapping("/api/v1/sectionEntity")
public class SectionsController {

	@Autowired
	private SectionService sectionService;

	@PostMapping
	public ResponseEntity<SectionWithClassResponse> createSections(@Valid @RequestBody SectionRequest request) {
		SectionWithClassResponse response = sectionService.createSections(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping
	public ResponseEntity<SectionWithClassResponse> updateSections(@Valid @RequestBody SectionRequest request) {
		SectionWithClassResponse response = sectionService.updateSections(request);
		return new ResponseEntity<>(response,
				(!ObjectUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{classId}")
	public ResponseEntity<SectionWithClassResponse> getAllSectionBySpecificClass(
			@PathVariable("classId") String classId) {
		SectionWithClassResponse response = sectionService.getAllSectionBySpecificClass(classId);
		return new ResponseEntity<>(response, (!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<List<SectionWithClassResponse>> getAllClassWithSections() {
		List<SectionWithClassResponse> response = sectionService.getAllClassWithSections();
		return new ResponseEntity<>(response,
				(!CollectionUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping
	public ResponseEntity<Boolean> deleteSections(@RequestBody @Valid SectionDeleteRequest request) {
		Boolean response = sectionService.deleteSections(request);
		return new ResponseEntity<>(response,
				(Boolean.TRUE.equals(response)) ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
