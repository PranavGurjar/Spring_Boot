package com.annotations.controller;

import com.annotations.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController        //11
public class MyController {
    @Autowired
    @Qualifier("student1")
    private Student student;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    //@ResponseBody    //9
    public Student home(@RequestBody Student stud){        //@RequestBody //10
        System.out.println("this is home method");
        stud.studying();
        this.student.setName("Pranav Mahajan");
        return this.student;
    }


    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String user(@PathVariable("userId") Integer userId){          //@PathVariable //12
        return String.valueOf(userId);
    }

}
