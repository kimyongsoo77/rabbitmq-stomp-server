package com.estory.daesang.dto.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RabbitMessageDto {
    private String title;
    private String message;
    private String uuid;
    private String type;
}

