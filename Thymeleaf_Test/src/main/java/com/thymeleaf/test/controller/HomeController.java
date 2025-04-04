package com.thymeleaf.test.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping("/")
    public String home(Model model, HttpSession session){
        session.setAttribute("message", "this is a session message");
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/services")
    public String services(){
        return "services";
    }

//    @RequestMapping("/contact")
//    public String contact(){
//        return "contact";
//    }
}
