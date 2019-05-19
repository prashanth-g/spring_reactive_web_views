package com.prashanth.spring.reactive.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GreetingsProducer {
    Flux<Greeting> greet(String name) {
        return Flux.fromStream(Stream.generate(new Supplier<Greeting>() {
            @Override
            public Greeting get() {
                return new Greeting("Hello "+name+ " @ " + Instant.now() + "!");
            }
        }));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Greeting {
    private String message;
}
