package com.scm.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	@GetMapping("/addContact")
	public String addContactForm(Model model) {
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "user/addContact";
	}
	
	@GetMapping("/processContact")
    public String handleInvalidGetRequest() {
        return "redirect:/user/addContact"; // Prevents 405 error when accessing this URL directly
    }
	
	//process add contact form
//	@Transactional
	@PostMapping("/processContact")
	public String processContact(@ModelAttribute Contact contact, @RequestParam("imageFile") MultipartFile file, Principal principal ) {
		
		try {
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);
			
			//processing and uploading file
			if(file.isEmpty()) {
				System.out.println("File is empty!");
			}else {
				//upload file in folder process
				contact.setImage(file.getOriginalFilename());
				File saveFile = new ClassPathResource("/static/images").getFile();
				//for file path know
				Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Image is Uploaded!");
			}
			
			contact.setUser(user);
			user.getContacts().add(contact);
			
			
			
			this.userRepository.save(user);
			
			System.out.println("Data : "+contact);
			System.out.println("Added to data base");
		}catch(Exception e) {
			System.out.println("Error : "+e.getMessage());
			e.printStackTrace();
		}
		
		return "/user/addContact";
	}
}
