package com.security;

import com.security.model.User;
import com.security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	Random random = new Random();

	public void createUser() {
		User user = new User();
		Long id = (long) random.nextInt(100); // Using long directly
		user.setId(id); // Consider removing this if using @GeneratedValue
		user.setEmail("user" + id + "@gmail.com");
		user.setUsername("user" + id);
		user.setPassword("user" + id);
		user.setEnabled(true);
		user.setRole("Admin");

		try {
			User save = userRepository.save(user);
			System.out.println("User saved: " + save);
		} catch (Exception e) {
			System.err.println("Error saving user: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createUser();
	}
}
