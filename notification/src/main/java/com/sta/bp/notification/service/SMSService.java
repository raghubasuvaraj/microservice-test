package com.sta.bp.notification.service;

import com.bp.common.client.vo.SMSReq;

public interface SMSService {
	Boolean sendSMS(SMSReq smsReq);

}
