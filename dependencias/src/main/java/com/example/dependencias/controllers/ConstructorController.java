package com.example.dependencias.controllers;

import com.example.dependencias.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/constructor")
public class ConstructorController {

    UserService userService;

    public ConstructorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String testId() {
        return this.getClass().getSimpleName() + " : " + userService.responseID();
    }

}
