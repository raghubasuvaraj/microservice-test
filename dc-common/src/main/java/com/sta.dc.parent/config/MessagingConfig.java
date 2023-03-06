package com.sta.dc.parent.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@ConditionalOnProperty(value="sta.messaging.enabled", havingValue = "true", matchIfMissing = false)
public class MessagingConfig {
    @Bean
    public Function<String, Object> producer() {
        return value -> value;
    }

    @Bean
    public Consumer<Object> consumer() {
        return message -> System.out.println(" message received " + message.toString());
    }
}
