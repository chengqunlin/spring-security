package com.example.springsecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouterController {

    @GetMapping(value = {"/","/index"})
    public String index() {
        return "static/index";
    }

    @GetMapping("/level1/a")
    public String toLogin(){
        return "level1/a";
    }
}
