package com.sta.settings.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sta.settings.dto.request.MasterFormsRequest;
import com.sta.settings.dto.response.MasterFormsResponse;
import com.sta.settings.service.MasterFormsService;

/**
 * Currently only minimum APIs are configuring.
 * 
 * @author r@ghu
 *
 */
@RestController
@RequestMapping("/api/v1/master-forms")
public class MasterFormsController {

	@Autowired
	private MasterFormsService masterFormsService;

//	@PostMapping
//	public ResponseEntity<MasterFormsResponse> createInstitutes(@Valid @RequestBody MasterFormsRequest request) {
//		MasterFormsResponse response = masterFormsService.createMasterForms(request);
//		return new ResponseEntity<>(response,
//				(!ObjectUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
//	}

//	@PutMapping
//	public ResponseEntity<MasterFormsResponse> updateMasterForms(@Valid @RequestBody MasterFormsRequest request) {
//		MasterFormsResponse response = masterFormsService.updateMasterForms(request);
//		return new ResponseEntity<>(response,
//				(!ObjectUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
//	}

//	@GetMapping("/by-curriculum/{curriculumId}")
//	public ResponseEntity<List<MasterFormsResponse>> getMasterFormByCarriculumn(
//			@PathVariable("curriculumId") String curriculumId) {
//		List<MasterFormsResponse> response = masterFormsService.getMasterFormByCarriculumn(curriculumId);
//		return new ResponseEntity<>(response, (!ObjectUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
}
