package com.scm.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.service.EmailService;

@Controller
public class ForgotController {
	Random random = new Random(1000);
	
	@Autowired
	private EmailService emailService;

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
		String message = "<h1> OTP : "+otp+"</h1>";
		String to = email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		
		if(flag) {
			session.setAttribute("otp", otp);
			return "verifyOtp";
		}
		else {
			
			session.setAttribute("message", "Check your email id !!");
			return "forgotEmailForm";
		}
		
	}
}
