package com.annotations;

import mypack.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
//1
@SpringBootApplication
public class SpringBootAnnotationApplication implements CommandLineRunner {
	//4
	@Autowired
	@Qualifier("student2")   //7
	private Student student;

	@Autowired
	private Date date;

	@Autowired
	private Emp emp;

	@Autowired
	private Dog dog;

	public static void main(String[] args) {
				//		OP 12 Annotations of Spring Boot Framework
				//1. @SpringBootApplication
				//@EnableAutoConfiguration + @ComponentScan + @Configuration
				//2. @Configration
				//3. @Bean
				//4. @Autowired
				//5. @Component
				//  @Controller (Component,MVC Contoller)
				//  @Service
				//  @Respository
				//6. @ComponentScan
				//7. @Qualifier
				//8. @Lazy
				//9. ResponseBody
				//10. RequestBody
				//11. @RestController
				//12. @PathVariable   //database se data nikal ne ke liye
				//@RequestParam("email") String email  //form se data nikal ne ke liye

		SpringApplication.run(SpringBootAnnotationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		this.student.studying();
		this.emp.whatsYourName();
//		this.dog.eating();
	}
}
