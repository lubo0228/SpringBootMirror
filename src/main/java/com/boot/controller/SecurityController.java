package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/loginsecurity")
    public String login() {
        return "login";
    }

    @GetMapping("/haha")
    public String haha() {
        return "haha";
    }

    @GetMapping("/hehe")
    public String hehe() {
        return "hehe";
    }

    @GetMapping("/exception")
    public String exception() {
        double a = 1 / 0;
        return "exception";
    }
}
