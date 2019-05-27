package com.prashanth.spring.reactive.web;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
@AllArgsConstructor
public class ThymeleafViewGreetingsController {

    private final GreetingsProducer greetingsProducer;

    @GetMapping("/greetings.do")
    String renderView() {
        return "greetings";
    }

    @GetMapping(value = "/greetings-view.do", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    String renderUpdatedView(@RequestParam("name") String name, Model model) {
        model.addAttribute("greetings", new ReactiveDataDriverContextVariable(this.greetingsProducer.greet(name), 1));
        return "greetings :: #greetings-block";
    }

}
