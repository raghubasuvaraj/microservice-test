package com.sta.bp.notification.service;

import com.bp.common.client.vo.EmailReq;
import com.bp.common.exception.model.CustomInternalServerException;

public interface EmailService {

	Boolean sendEmail(EmailReq emailReq)  throws CustomInternalServerException;
}
