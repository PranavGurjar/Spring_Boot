package com.scm.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import com.razorpay.*;

import org.json.JSONObject;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scm.dao.ContactRepository;
import com.scm.dao.MyOrderRepository;
import com.scm.dao.UserRepository;
import com.scm.entities.Contact;
import com.scm.entities.MyOrder;
import com.scm.entities.User;
import com.scm.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private MyOrderRepository myOrderRepository;
	
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
	public String deleteContact(@PathVariable("cid") Integer cId, Model model, HttpSession session, Principal principal) {
		
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
		
		User user = this.userRepository.getUserByUserName(principal.getName());
		user.getContacts().remove(contact);
		this.userRepository.save(user);

//		contact.setUser(null);
//		this.contactRepository.delete(contact);
//		
		
		System.out.println("Deleted!");
		session.setAttribute("message", new Message("Contact deleted successfully!!", "success"));	

		return "redirect:/user/showContact/0";
	}
	
	//open update form handler
	@PostMapping("/updateContact/{cid}")
	public String updateForm(@PathVariable("cid") Integer cId, Model model) {
		model.addAttribute("title", "Update Contact");
		
		Contact contact = this.contactRepository.findById(cId).get();
		model.addAttribute("contact", contact);
		
		return "/user/updateForm";
	}
	
	//update contact handler
	@RequestMapping(value = "processUpdate", method = RequestMethod.POST)
	public String updateHandler(@ModelAttribute Contact contact, @RequestParam("imageFile") MultipartFile file, Model model, HttpSession session, Principal principal) {
		try {
			
			Contact oldContactDetails = this.contactRepository.findById(contact.getcId()).get();
			
			//image
			if(!file.isEmpty()) {
				
				//file work 
				//rewrite
				
				//delete old photo
				File deleteFile = new ClassPathResource("/static/images").getFile();
				File file1 = new File(deleteFile, oldContactDetails.getImage());
				file1.delete();
				
				
				//update new photo
				File saveFile = new ClassPathResource("/static/images").getFile();
				
				//for file path know
				Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
				
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				
				contact.setImage(file.getOriginalFilename());
				
				
			}else {
				contact.setImage(oldContactDetails.getImage());
			}
			
			User user = this.userRepository.getUserByUserName(principal.getName());
			contact.setUser(user);
			
			this.contactRepository.save(contact);
			
			session.setAttribute("message", new Message("Your contact is updated!", "success"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Contact Id : "+contact.getcId());
		System.out.println("Contact Name : "+contact.getName());
		return "redirect:/user/"+contact.getcId()+"/contact";
	}
	
	//your profile handler
	@GetMapping("/profile")
	public String yourProfile() {
		return "/user/profile";
	}
	
	
	//open setting handler
	@GetMapping("/settings")
	public String openSettings() {
		return "/user/settings";
	}
	
	
	//change password...
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Principal principal, HttpSession session) {
		System.out.println("Old Password : "+oldPassword);
		System.out.println("New Password : "+newPassword);
		
		String userName = principal.getName();
		User currentUser = this.userRepository.getUserByUserName(userName);
		System.out.println("Password : "+currentUser.getPassword());
		
		if(this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
			//change the password
			currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
			this.userRepository.save(currentUser);
			
			session.setAttribute("message", new Message("Your password is successfully changed!", "success"));
		}
		else {
			//error...
			session.setAttribute("message", new Message("Please Enter correct old password!", "danger"));
			return "redirect:/user/settings";
		}
		
		return "redirect:/user/index";
	}
	
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(@RequestBody Map<String, Object> data, Principal principal) throws Exception {
//		System.out.println("createOrder");
		System.out.println(data);
		
		int amount = Integer.parseInt(data.get("amount").toString());
		
		//java 11 allow var
//		var client = new RazorpayClient("rzp_test_xGyx3FPMpN9fux", "v1cFSCKfW8EncSTHSwRlIH0z");
		
		RazorpayClient client = new RazorpayClient("rzp_test_xGyx3FPMpN9fux", "v1cFSCKfW8EncSTHSwRlIH0z");
		JSONObject ob = new JSONObject();
		ob.put("amount", amount*100);
		ob.put("currency", "INR");
		ob.put("receipt", "txn_235425");
		
		//creating new order
		Order order = client.Orders.create(ob);
		System.out.println(order);
		
		//save the order in database
		MyOrder myOrder = new MyOrder();
		
		myOrder.setAmount(order.get("amount")+"");
		myOrder.setOrderId(order.get("id"));
		myOrder.setPaymentId(null);
		myOrder.setStatus("created");
		myOrder.setUser(this.userRepository.getUserByUserName(principal.getName()));
		myOrder.setReceipt(order.get("receipt"));
		
		this.myOrderRepository.save(myOrder);
		
		//if you want you can save this to your database
		
		return order.toString();
	}
	
	@PostMapping("/updateOrder")
	public ResponseEntity<?> updateOrder(@RequestBody Map<String, Object> data){
		
		System.out.println(data);
		MyOrder myOrder = this.myOrderRepository.findByOrderId(data.get("order_id").toString());
		
		myOrder.setPaymentId(data.get("payment_id").toString());
		myOrder.setStatus(data.get("status").toString());
		
		this.myOrderRepository.save(myOrder);
		
		return ResponseEntity.ok(Map.of("msg", "updated"));
	}
	
}
