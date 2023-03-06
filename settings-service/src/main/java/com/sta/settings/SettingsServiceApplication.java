package com.sta.settings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
@EntityScan({"com.sta.*"})
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class, value = {
		"com.sta.*"})
@ComponentScan({"com.sta.*"})
public class SettingsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettingsServiceApplication.class, args);
	}

}
