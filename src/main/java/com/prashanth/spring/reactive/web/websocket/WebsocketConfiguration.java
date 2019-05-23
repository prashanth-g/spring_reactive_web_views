package com.prashanth.spring.reactive.web.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebsocketConfiguration {

    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler webSocketHandler) {
        return new SimpleUrlHandlerMapping() {
            {
                setOrder(10);
                Map<String, Object> urlMap = new HashMap<>();
                urlMap.put("/ws/greetings", webSocketHandler);
                setUrlMap(urlMap);
            }
        };
    }
}
