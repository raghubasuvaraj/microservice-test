package com.sta.bp.notification.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private MailConfig mail;
    private Rabbitmq rabbitmq;

    //------- MailGun -------------
    @Setter
    @Getter
    public static class MailConfig {
        private String host;
        private String port;
        private String username;
        private String password;
    }
    
    
    

    //------- Rabbit MQ -------------
    @Setter
    @Getter
    public static class Rabbitmq {
        private String queue;
        private String routingKey;
        private String exchange;
    }
}
