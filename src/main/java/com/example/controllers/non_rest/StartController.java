package com.example.controllers.non_rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.util.constant.Constants.INDEX_PAGE;

@Controller
@RequestMapping("/api/start")
public class StartController {
    @GetMapping("/")
    public String goToStartPage() {
        return INDEX_PAGE;
    }
}
