package com.sta.bp.notification.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
	
	
	@Autowired
	AppProperties appProperties;

	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(this.appProperties.getMail().getHost());
	    mailSender.setPort(Integer.parseInt(this.appProperties.getMail().getPort()));
	    
	    mailSender.setUsername(this.appProperties.getMail().getUsername());
	    mailSender.setPassword(this.appProperties.getMail().getPassword());
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
	
}
