package com.epam.lab.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/controller")
public class TestController {

    @RequestMapping
    public String returnString() {
        return "Hello World!";
    }
}
