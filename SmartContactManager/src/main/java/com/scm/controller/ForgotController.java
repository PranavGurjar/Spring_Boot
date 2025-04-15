package com.scm.controller;

import java.security.Principal;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.dao.UserRepository;
import com.scm.entities.User;
import com.scm.service.EmailService;

@Controller
public class ForgotController {
	Random random = new Random();
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository userRepository;

	//email id form open handler
	@RequestMapping("/forgot")
	public String openEmailForm() {
		return "forgotEmailForm";
	}
	
	@PostMapping("/sendOtp")
	public String sendOtp(@RequestParam("email") String email, HttpSession session) {
		System.out.println("Email : "+email);
		
		//generating otp of 4 digit
		int otp = random.nextInt(999999);  //99999999
		System.out.println("OTP : "+otp);
		
		//write code for send OTP to email
		String subject = "OTP From SCM";
		//String message = "<h1> OTP : "+otp+"</h1>";
		String message = ""
						+"<div style='border : 1px solid #e2e2e2; padding : 20px'>"
						+"<h1>"
						+"OTP is "
						+"<b>"+otp+"</b>"
						+"</h1>"
						+"</div>";
				
		String to = email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		
		if(flag) {
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			return "verifyOtp";
		}
		else {
			
			session.setAttribute("message", "Check your email id !!");
			return "forgotEmailForm";
		}
		
	}
	
	
	//verify otp
	@PostMapping("/verifyOtp")
	public String verifyOtp(@RequestParam("otp") int otp, HttpSession session) {
		int myOtp = (int)session.getAttribute("myotp");
		String email = (String)session.getAttribute("email");
		
		if (myOtp==otp) {
			//password change form
			User user = this.userRepository.getUserByUserName(email);
			
			if(user==null) {
				//send error message
				session.setAttribute("message", "User not exists with this email !!");
				return "forgotEmailForm";
			}
			else {
				//send password change form
				
			}
			
			return "passwordChangeForm";
		}
		else {
			session.setAttribute("message", "You have entered wrong otp !!");
			return "verifyOtp";
		}
	}
}
