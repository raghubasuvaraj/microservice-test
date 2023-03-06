package com.sta.dc.academic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;

@EntityScan({"com.sta.dc.common.entity"})
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class, value = {
        "com.sta.dc.common.repository"})
@ComponentScan({"com.sta.dc.common", "com.sta.dc.academic.*"})

@SpringBootApplication
@EnableEurekaClient
public class AcademicsServiceApplication {

    public static void main(String[] args) {
        try {
			SpringApplication.run(AcademicsServiceApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}
    }

}
