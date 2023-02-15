package com.estory.daesang.service.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {
    @Value("${rabbitmq.estory.exchange}")
    private String topicExchangeName;

    @Value("${rabbitmq.estory.key}")
    private String keyName;

    final RabbitTemplate rabbitTemplate;

    public void sendMessage(RabbitMessage message, String type, String uuid) {
        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/json");
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("uuid", uuid);
        properties.setHeaders(map);

        rabbitTemplate.convertAndSend(topicExchangeName, keyName, MessageBuilder.withBody( message.toString().getBytes()).andProperties(properties).build());
        // rabbitTemplate.convertAndSend( message);
    }
}
