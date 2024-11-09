package com.czechtutor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApplicationController {
    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}
