package com.estory.daesang.EventListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
public class RabbitListener {

    @Autowired
    private SimpMessagingTemplate webSocket;


    //기본 수신
     @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "${rabbitmq.estory.queue}")
    public void consume(Message message) throws JsonProcessingException {
        log.info("Consume Message: {}", message );
        log.info("Consume Message: {}", new String(message.getBody(), StandardCharsets.UTF_8) );
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        String type = headers.containsKey("type") ? headers.get("type").toString() : "";
        String uuid = headers.containsKey("uuid") ? "/"+headers.get("uuid").toString() : "";
        if("STORE".equals( type )) {
            webSocket.convertAndSend("/store"+uuid, new String(message.getBody(), StandardCharsets.UTF_8));
        } else if("CUSTOMER".equals( type )) {
            webSocket.convertAndSend("/customer"+uuid , new String(message.getBody(), StandardCharsets.UTF_8));
        } else {
            webSocket.convertAndSend("/topics"+uuid , new String(message.getBody(), StandardCharsets.UTF_8));
        }
    }
}
