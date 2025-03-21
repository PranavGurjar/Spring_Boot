package com.practice.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	// handler for thymeleaf
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model) {
		System.out.println("Inside About Handler");
		// putting data in the model
		model.addAttribute("name", "Pranav Mahajan");
		model.addAttribute("currentDate", new Date().toLocaleString());
		return "about";
		// about.html
	}

	// handler for iterator
	@GetMapping("/example-loop")
	public String iterateHandler(Model model) {
		List<String> names = List.of("Ankit", "Laxmi", "Karan", "John");
		model.addAttribute("names", names);
		return "iterate";
	}

	// handler for conditional statement
	@GetMapping("/condition")
	public String conditionalHandler(Model m) {
		System.out.println("Conditional handler executed...");
		m.addAttribute("isActive", false);
		m.addAttribute("gender", "F");

		List<Integer> list = List.of(233, 33, 10, 20, 30, 50);
		m.addAttribute("myList", list);
		return "condition";
	}

	// handler for including fragment
	@GetMapping("/service")
	public String serviceHandler(Model m) {
		// processing logic
		m.addAttribute("title", "I like you");
		m.addAttribute("subtitle", LocalDateTime.now().toString());
		return "service";
	}

	@GetMapping("/base")
	public String baseHandler() {

		return "base";
	}

	// for new about
	@GetMapping("/aboutNew")
	public String aboutNewHandler() {
		return "aboutNew";
	}

	// for new about
	@GetMapping("/contact")
	public String contactHandler() {
		return "contact";
	}

}
