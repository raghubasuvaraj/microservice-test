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

import com.bp.common.client.vo.EmailReq;
import com.bp.common.exception.model.CustomInternalServerException;
import com.bp.common.utils.AppConstant;
import com.sta.bp.notification.service.EmailService;

//1100
@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	EmailService emailService;

	
	
	/**
	 * Generic API to send Email
	 *
	 * @param emailReq
	 *
	 * @return boolean
	 * @throws CustomInternalServerException
	 *
	 * @apiNote BPB-38 | 1101
	 */
	@PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> sendEmail(@Valid @RequestBody EmailReq emailReq)
			throws CustomInternalServerException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.emailService.sendEmail(emailReq));
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomInternalServerException("N-1101", AppConstant.DEFAULT_ERROR_MSG, e);
		}
	}
}
