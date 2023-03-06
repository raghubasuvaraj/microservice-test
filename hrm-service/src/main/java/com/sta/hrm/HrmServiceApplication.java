package com.sta.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HrmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmServiceApplication.class, args);
	}

}
