package com.multiple.db;

import com.multiple.db.mysql.entities.User;
import com.multiple.db.mysql.repo.UserRepo;
import com.multiple.db.postgresql.entities.Product;
import com.multiple.db.postgresql.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMultipleDbApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ProductRepo productRepo;


	public static void main(String[] args) {
		SpringApplication.run(SpringMultipleDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = User.builder()
						.firstName("Pranav")
						.lastName("Mahajan")
						.email("prm@gmail.com")
						.build();



		Product product = Product.builder()
								.name("Apple IPhone")
								.description("This is a Apple Iphone Product")
								.live(true)
								.price(54000)
								.build();


		productRepo.save(product);
		userRepo.save(user);
		System.out.println("Data Saved!!");

		getData();
	}

	public void getData(){
		productRepo.findAll().forEach(product -> System.out.println(product.getName()));
		userRepo.findAll().forEach(user -> System.out.println(user.getFirstName()));
	}
}


