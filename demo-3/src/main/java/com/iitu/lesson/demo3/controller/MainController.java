package com.iitu.lesson.demo3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MainController.CONTROLLER_PATH)
public class MainController {
    public static final String CONTROLLER_PATH = "/main";

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
