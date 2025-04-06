package com.scm.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;



//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.scm.dao.ContactRepository;
import com.scm.dao.UserRepository;
import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
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
	public String processContact(@ModelAttribute Contact contact, @RequestParam("imageFile") MultipartFile file, 
			Principal principal, HttpSession session ) {
		
		try {
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);
			
			/*
			 * if(3>2) { throw new Exception(); }
			 */
			
			//processing and uploading file
			if(file.isEmpty()) {
				System.out.println("File is empty!");
				contact.setImage("contact.png");
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
			System.out.println("Added to database");
			
			//success message
			session.setAttribute("message", new Message("Your contact is added!! Add more...","success"));
			
		}catch(Exception e) {
			System.out.println("Error : "+e.getMessage());
			e.printStackTrace();
			
			//error message
			session.setAttribute("message", new Message("Something went wrong!! try again...","danger"));
			
		}
		
		return "/user/addContact";
	}
	
	//show contact handler
	//per page = 5[n]
	//current page = 0 [page]
	@GetMapping("/showContact/{page}")
	public String showContact(@PathVariable("page") Integer page, Model model, Principal principal) {              
		model.addAttribute("title", "Show Contacts");
		
		//show contact list
		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);	
		
//		List<Contact> contacts = user.getContacts();
		//currentpage-page
		//contact per page-5
		Pageable pageable = PageRequest.of(page, 5);
		Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(), pageable);
		model.addAttribute("contacts", contacts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", contacts.getTotalPages());
		
		return "/user/showContact";
	}
	 
	//showing particular contact details
	@RequestMapping("/{cId}/contact")
	public String showContactDetails(Model model, @PathVariable("cId") Integer cId, Principal principal) {
		model.addAttribute("title", "Contact Detail");
		System.out.println("CID : "+cId);
		Optional<Contact> contactOptional = this.contactRepository.findById(cId);
		Contact contact = contactOptional.get();
		
		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);
		
		if(user.getId()==contact.getUser().getId()) {
			model.addAttribute("contact", contact);
			model.addAttribute("title", contact.getName());
		}
		
		return "/user/contactDetail";
	}
	
	@GetMapping("/delete/{cid}")
	public String deleteContact(@PathVariable("cid") Integer cId, Model model, HttpSession session) {
		
		Contact contact = this.contactRepository.findById(cId).get();
		System.out.println("Contact : "+contact.getcId());
		
		//check assignment
		
		
		model.addAttribute("title", contact.getName());

		//remove image
		//contact.getImage()
		 // Delete the image file
		/*
		 * try { String imageName = contact.getImage(); if
		 * (!imageName.equals("default.png")) { // don't delete default image String
		 * uploadPath = new ClassPathResource("static/img").getFile().getAbsolutePath();
		 * File imageFile = new File(uploadPath + File.separator + imageName); if
		 * (imageFile.exists()) { imageFile.delete();
		 * System.out.println("Image deleted: " + imageFile.getAbsolutePath()); } } }
		 * catch (Exception e) { e.printStackTrace(); }
		 */

		contact.setUser(null);
		this.contactRepository.delete(contact);
		session.setAttribute("message", new Message("Contact deleted successfully!!", "success"));	

		return "redirect:/user/showContact/0";
	}
	
	@GetMapping("/update/{cid}")
	public String updateContact(@PathVariable("cid") Integer cId, Model model) {
		
		Optional<Contact> contactOptional = this.contactRepository.findById(cId);
		Contact contact = contactOptional.get();
		this.contactRepository.delete(contact);
		
		return "";
	}
}
