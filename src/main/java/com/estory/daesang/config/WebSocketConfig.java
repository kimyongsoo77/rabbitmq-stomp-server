package com.estory.daesang.config;

import com.estory.daesang.Interceptor.HttpHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/webSocket")
				.setAllowedOriginPatterns("*").addInterceptors(new HttpHandshakeInterceptor()).withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// topic => 1:N / queue => 1:1
		// enableSimpleBroker : 해당경로를 구독하는 클라이언트에게 메시지를 전달
		registry.enableSimpleBroker("/store", "/customer","/topic");
		// setApplicationDestinationPrefixes : client에서 SEND 요청을 처리
		registry.setApplicationDestinationPrefixes("/");
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
		registration.setMessageSizeLimit(100 * 64 * 1024);    // Max incoming message size, default : 64 * 1024
		registration.setSendTimeLimit(20 * 10000);            // default : 10 * 10000
		registration.setSendBufferSizeLimit(10 * 512 * 1024); // Max outgoing buffer size, default : 512 * 1024
	}

}