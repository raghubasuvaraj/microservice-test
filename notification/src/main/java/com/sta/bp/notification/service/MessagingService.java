package com.sta.bp.notification.service;

import com.bp.common.dto.MqPayload;
import com.bp.common.exception.model.CustomInternalServerException;

public interface MessagingService {
	
	boolean publishMessageToQueue(MqPayload mqPayload) throws CustomInternalServerException;

}
