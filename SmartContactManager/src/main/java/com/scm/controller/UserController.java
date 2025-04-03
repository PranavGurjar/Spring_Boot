package com.scm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.dao.UserRepository;
import com.scm.entities.Contact;
import com.scm.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("UserName : "+userName);
		
		//get the user using username(Email)
		User user = userRepository.getUserByUserName(userName);
		System.out.println("USER : "+user);
		model.addAttribute("user", user);
	}
	
	
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal){
		model.addAttribute("title", "User Dashboard");
		return "user/user_dashboard";
	}
	
	
	
	//add contact handler
	@RequestMapping("/addContact")
	public String addContactForm(Model model) {
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "user/addContact";
	}
	
	
}
