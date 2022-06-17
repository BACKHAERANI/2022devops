package com.Assembble.carbble.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TimeController {

    @GetMapping("/")
    public String home() {
        LocalDateTime now = LocalDateTime.now();
        return now.toString();
    }
}
