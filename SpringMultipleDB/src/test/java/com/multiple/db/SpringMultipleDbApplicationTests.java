package com.multiple.db;

import com.multiple.db.mysql.entities.User;
import com.multiple.db.mysql.repo.UserRepo;
import com.multiple.db.postgresql.entities.Product;
import com.multiple.db.postgresql.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringMultipleDbApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ProductRepo productRepo;

	@Test
	public void dbTest(){
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
	}

	@Test
	public void getData(){
		productRepo.findAll().forEach(product -> System.out.println(product.getName()));
		userRepo.findAll().forEach(user -> System.out.println(user.getFirstName()));
	}

}
