package com.prashanth.spring.reactive.web;

import com.prashanth.spring.reactive.web.model.Greeting;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingsProducer greetingsProducer;

    @GetMapping(value = "/greetings/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Publisher<String> greet(@PathVariable String name) {
        return this.greetingsProducer.greet(name).map(Greeting::getMessage);
    }

}
