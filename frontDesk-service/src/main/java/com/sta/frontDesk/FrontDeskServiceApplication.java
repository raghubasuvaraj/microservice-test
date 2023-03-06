package com.sta.frontDesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FrontDeskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontDeskServiceApplication.class, args);
	}

}
