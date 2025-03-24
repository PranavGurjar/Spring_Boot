package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scm.dao.UserRepository;
import com.scm.entities.Contact;
import com.scm.entities.User;

@Controller
public class HomeController {
	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@GetMapping("/test")
//	@ResponseBody
//	public String test() {
//		User user = new User();
//		user.setName("Pranav Mahajan");
//		user.setEmail("abc@gmail.com");
//		
////		Contact contact = new Contact();
////		user.getContacts().add(contact);
//		
//		userRepository.save(user);
//		return "Working";
//	}
}
