package com.prashanth.spring.reactive.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafViewGreetingsController {

    @GetMapping("/greetings.do")
    String renderView() {
        return "greetings";
    }

}
