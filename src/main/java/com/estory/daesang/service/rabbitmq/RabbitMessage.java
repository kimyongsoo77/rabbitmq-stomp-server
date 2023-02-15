package com.estory.daesang.service.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RabbitMessage {
    private String title;
    private String message;

    @Override
    public String toString() {
        return "{" +
                "\"title\":\"" + title + '\"' +
                ", \"message\":\"" + message + '\"' +
                '}';
    }
}

