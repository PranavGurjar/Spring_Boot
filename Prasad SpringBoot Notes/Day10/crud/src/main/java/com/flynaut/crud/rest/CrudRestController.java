package com.flynaut.crud.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class CrudRestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Team";
    }
}
