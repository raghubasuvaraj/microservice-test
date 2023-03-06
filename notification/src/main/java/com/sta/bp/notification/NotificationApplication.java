package com.sta.bp.notification;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import com.sta.bp.notification.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;

@EnableConfigurationProperties(AppProperties.class)
@EntityScan({"com.bp.common.entity"})
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class, value = {
		"com.bp.common.repository"})
@EnableFeignClients(basePackages = {"com.bp.common.client"})
@ComponentScan({"com.bp.common", "com.sta.bp"})
@EnableEurekaClient
@EnableJpaAuditing
@SpringBootApplication
public class NotificationApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

}
