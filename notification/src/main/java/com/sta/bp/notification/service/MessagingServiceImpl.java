package com.sta.bp.notification.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bp.common.dto.MqPayload;
import com.bp.common.exception.model.CustomInternalServerException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MessagingServiceImpl implements MessagingService {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public boolean publishMessageToQueue(MqPayload mqPayload) throws CustomInternalServerException{
		try {
			String data = objectMapper.writeValueAsString(mqPayload.getData());
			log.info("Queue:{}, Payload: {}",mqPayload.getQueueName(), data);
			rabbitTemplate.convertAndSend(mqPayload.getQueueName(), data);
			return false;
		} catch (Exception e) {
			throw new CustomInternalServerException(null, null, null);
		}
	}

}
