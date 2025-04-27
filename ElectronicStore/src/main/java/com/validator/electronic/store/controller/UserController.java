package com.validator.electronic.store.controller;

import com.validator.electronic.store.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @PostMapping("/user")
    public String userDetails(@Valid @RequestBody UserDto userDto){


        return "UserDto{" +
                "userId='" + userDto.getUserId() + '\'' +
                ", name='" + userDto.getName() + '\'' +
                ", email='" + userDto.getEmail() + '\'' +
                ", password='" + userDto.getPassword() + '\'' +
                ", gender='" + userDto.getGender() + '\'' +
                ", about='" + userDto.getAbout() + '\'' +
                ", imageName='" + userDto.getImageName() + '\'' +
                '}';



        /*
        * {
                "userId": 101,
                "name": "Pranav Mahajan",
                "email": "prm@gmail.com",
                "password": "prm123",
                "gender": "Male",
                "about": "I am a Software developer",
                "imageName": "prm.jpg"
            }
        * */
    }



}
