package com.estory.daesang.controller;

import com.estory.daesang.dto.rabbitmq.RabbitMessageDto;
import com.estory.daesang.service.rabbitmq.Producer;
import com.estory.daesang.service.rabbitmq.RabbitMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RabbitMqController {

    @Autowired
    private Producer producer;


    @PostMapping("/addMessage")
    public String test(@Valid @RequestBody RabbitMessageDto body) {
        RabbitMessage msg = new RabbitMessage(body.getTitle(), body.getMessage());
        producer.sendMessage(msg, body.getType(), body.getUuid());
        return "success";
    }
}
