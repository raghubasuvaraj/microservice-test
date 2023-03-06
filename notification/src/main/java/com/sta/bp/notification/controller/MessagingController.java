package com.sta.bp.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.common.dto.MqPayload;
import com.bp.common.exception.model.CustomInternalServerException;
import com.bp.common.utils.AppConstant;
import com.sta.bp.notification.service.MessagingService;

@RequestMapping("/messaging")
@RestController
public class MessagingController {
	
	@Autowired
	private MessagingService messagingService;

	@PostMapping(value = "/internal/publish-to-queue")
	public ResponseEntity<Boolean> publicMessageToQueue(@RequestBody MqPayload mqPayload)
			throws CustomInternalServerException {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.messagingService.publishMessageToQueue(mqPayload));
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomInternalServerException(null, AppConstant.DEFAULT_ERROR_MSG, e);
		}
	}

}
