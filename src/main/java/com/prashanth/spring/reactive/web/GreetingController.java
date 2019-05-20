package com.prashanth.spring.reactive.web;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingsProducer greetingsProducer;

    @GetMapping(value = "/greetings/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Publisher<Greeting> greet(@PathVariable String name) {
        return this.greetingsProducer.greet(name);
    }

}
