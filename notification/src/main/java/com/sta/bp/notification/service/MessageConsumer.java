package com.sta.bp.notification.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
	
	@RabbitListener(queues = {"${app.rabbitmq.queue}"})
    public void receive(@Payload String fileBody) {
        System.out.println("Message " + fileBody);
    }
	
	
	@RabbitListener(queues = "test.q1")
    public void receive2(@Payload String fileBody) {
        System.out.println("Message-test.q1 " + fileBody);
    }

}
