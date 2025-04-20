/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netbeans.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PRANAV
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public ResponseEntity<?> getUser(){
        Map<String, Object> user = new HashMap<>();
        user.put("name", "Pranav");
        user.put("city", "Kanpur");
        
        return ResponseEntity.ok(user);
    }
}
