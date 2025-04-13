package com.jwt.controller;


import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping("/welcome")
    public String welcome(){
        String text = "this is private page";
        text+="this page is not allowed to unauthenicated users";
        return text;
    }

    @RequestMapping("/getUsers")
    public String getUser(){
        return "{\'name\' : \'Pranav\'}";
    }
}
