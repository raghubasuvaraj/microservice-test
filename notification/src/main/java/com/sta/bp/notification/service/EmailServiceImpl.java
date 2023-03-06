package com.sta.bp.notification.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bp.common.client.vo.EmailReq;
import com.bp.common.entity.master.SystemFiles;
import com.bp.common.exception.model.CustomInternalServerException;
import com.bp.common.utils.AppConstant;

import lombok.extern.slf4j.Slf4j;


//2100
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Environment environment;

	//2101
	@Override
	public Boolean sendEmail(EmailReq emailReq) throws CustomInternalServerException {
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			mail.setFrom("info@bankerspolis.com");
			
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(emailReq.getToEmail());
			helper.setSubject(emailReq.getSubject());
			helper.setText(emailReq.getBody(), true);
			
			//attachment 
			if(!Objects.isNull(emailReq.getSystemFileIds()) && emailReq.getSystemFileIds().length > 0) {
				for(String systemFileId : emailReq.getSystemFileIds()) {
					SystemFiles systemFile = new SystemFiles();
					FileSystemResource file = new FileSystemResource(new File(""));
					helper.addAttachment(systemFile.getFileOriginalName(), file);
				}
			}
			
			List<String> profiles = Arrays.asList(environment.getActiveProfiles());
			if(profiles.contains("local") || profiles.contains("dev")) {
				helper.setTo("sandip_g@socialtalentagency.com");
			}
				
			
	        mailSender.send(mail);
	        log.info("Mail Sent!");
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CustomInternalServerException("N-2101-02", AppConstant.DEFAULT_ERROR_MSG,e);
		}
	}

}
