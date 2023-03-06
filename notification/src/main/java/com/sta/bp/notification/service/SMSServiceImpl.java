package com.sta.bp.notification.service;

import org.springframework.stereotype.Service;

import com.bp.common.client.vo.SMSReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SMSServiceImpl implements SMSService{

	@Override
	public Boolean sendSMS(SMSReq smsReq) {
		try {
			//SMTP server integration
			log.info("sms Sent");
			//SMTP server integration -end
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
