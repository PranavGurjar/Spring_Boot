package com.scm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scm.dao.UserRepository;
import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helper.Message;


import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-SCM");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About-SCM");
		return "about";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "Login-SCM");
		return "login";
	}

	@RequestMapping("/signup")
	public String signup(Model model, HttpSession session) {
		model.addAttribute("title", "Register-SCM");
		model.addAttribute("user", new User());
		
		// Ensure session message is removed after being read
        session.removeAttribute("message");
		
		return "signup";
	}

	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {
		
		try {
			
			if(!agreement) {
				System.out.println("You not agreed term and condition");
				throw new Exception("You not agreed term and condition");
			}
			
			if(result.hasErrors()) {
				System.out.println("ERROR "+result.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			System.out.println("Agreement = "+agreement);
			System.out.println("User = "+user);
			
			User result1 = this.userRepository.save(user);
			
			model.addAttribute("user", new User());
			
			session.setAttribute("message", new Message("Successfully Registered!!!", "alert-success"));
//			return "signup";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong!!!"+e.getMessage(), "alert-danger"));
//			return "signup";
		}
		return "signup";
		
	}

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
