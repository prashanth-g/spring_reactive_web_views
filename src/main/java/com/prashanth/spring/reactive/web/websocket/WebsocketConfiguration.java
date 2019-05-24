package com.prashanth.spring.reactive.web.websocket;

import com.prashanth.spring.reactive.web.GreetingsProducer;
import com.prashanth.spring.reactive.web.model.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    WebSocketHandler webSocketHandler(GreetingsProducer greetingsProducer) {
        return new WebSocketHandler() {
            @Override
            public Mono<Void> handle(WebSocketSession session) {
                Flux<WebSocketMessage> message = session.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .flatMap(greetingsProducer::greet)
                        .map(Greeting::getMessage)
                        .map(session::textMessage);
                return session.send(message);
            }
        };
    }
}
