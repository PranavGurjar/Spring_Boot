package com.file.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<?> addUserInformation(@RequestParam("file")MultipartFile file, @RequestParam("userData") String userData) {

        this.logger.info("add user request");

        this.logger.info("File : {}", file.getOriginalFilename());
        //this.logger.info("User : {}", userData);

        //converting string into json
        User user = null;
        try {
            user = this.mapper.readValue(userData, User.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }

        //file save
        //user save
        this.logger.info("User data is : {}",user);

        return ResponseEntity.ok(user);
    }

}
