package com.deploy.war.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @GetMapping("/users")
    public List getUsers(){
        return List.of(
                "Pranav",
                "Yash",
                "Kunal"
        );
    }
}
