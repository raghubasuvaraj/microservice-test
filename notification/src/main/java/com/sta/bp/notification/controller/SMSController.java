package com.sta.bp.notification.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.common.client.vo.SMSReq;
import com.bp.common.exception.model.CustomInternalServerException;
import com.bp.common.utils.AppConstant;
import com.sta.bp.notification.service.SMSService;

@RestController
@RequestMapping("/sms")
public class SMSController {
	@Autowired
	SMSService smsService;

	// 1201
	@PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> sendSMS(@Valid @RequestBody SMSReq smsReq) throws CustomInternalServerException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.smsService.sendSMS(smsReq));
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomInternalServerException("N-1101", AppConstant.DEFAULT_ERROR_MSG, e);
		}
	}
}
