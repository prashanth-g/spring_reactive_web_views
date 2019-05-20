package com.prashanth.spring.reactive.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GreetingsProducer {
    Flux<Greeting> greet(String name) {
        return Flux
                .fromStream(Stream.generate(() -> new Greeting("Hello "+name+ " @ " + Instant.now() + "!")))
                .delayElements(Duration.ofSeconds(1));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Greeting {
    private String message;
}
