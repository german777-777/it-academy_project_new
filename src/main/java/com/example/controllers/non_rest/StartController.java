package com.example.controllers.non_rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping("/")
    public String goToStartPage() {
        return "index";
    }
}
