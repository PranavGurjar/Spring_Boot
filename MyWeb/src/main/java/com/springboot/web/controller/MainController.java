package com.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/home")
	public String home() {
		System.out.println("This is home page");
		return "home";
	}
	
	
	@RequestMapping("/contact")
	public String contact() {
		System.out.println("This is contact page");
		return "contact";
	}
}
