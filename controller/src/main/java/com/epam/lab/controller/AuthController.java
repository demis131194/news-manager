package com.epam.lab.controller;

import com.epam.lab.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@CrossOrigin("http://localhost:3000")
@Validated
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return "LOGIN - " + username + " " + password;
    }


}
