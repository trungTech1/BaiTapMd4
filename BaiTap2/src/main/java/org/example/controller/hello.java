package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class hello {
    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/dictionary")
    public String helloPost() {
        return "dictionary";
    }

}
