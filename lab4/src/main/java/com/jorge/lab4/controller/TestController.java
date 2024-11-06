package com.jorge.lab4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "Endpoint publico!";
    }

    @GetMapping("/secure/hello")
    public String secureEndpoint(@RequestHeader("Authorization") String authHeader) {
        return "Endpoint protegido!";
    }
}

