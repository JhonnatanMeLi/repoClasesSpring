package com.example.dependencias.controllers;

import com.example.dependencias.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autowired")
public class AutowiredController {

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public String testId() {
        return this.getClass().getSimpleName() + " : " + userService.responseID();
    }

}
