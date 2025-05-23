package com.jwt.controller;

import com.jwt.entities.User;
import com.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;


    //  http://localhost:8080/home/user
    @GetMapping("/user")
    public List<User> getUser(){
        System.out.println("getting users");
        return this.userService.getUsers();
    }

    //  http://localhost:8080/home/currentUser
    @GetMapping("/currentUser")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
