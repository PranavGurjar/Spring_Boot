package com.debug.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/about")
    public String getAbout(){
        String str = "Hello sir i am Pranav Mahajan";
        str = str.toLowerCase();
//        System.out.println(str);
        str = str.toUpperCase();
//        System.out.println(str);
        return str;
    }
}
