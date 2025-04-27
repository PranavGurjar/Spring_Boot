package com.orm;

import com.orm.entity.*;
import com.orm.repo.CategoryRepo;
import com.orm.repo.ProductRepo;
import com.orm.repo.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BootOrmApplication implements CommandLineRunner {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ProductRepo productRepo;

	private Logger logger = LoggerFactory.getLogger(Student.class);

	public static void main(String[] args) {
		SpringApplication.run(BootOrmApplication.class, args);
	}



//	//OneToOne
//	@Override
//	public void run(String... args) throws Exception {
//
//		//One To One Mappings
//
//		//add student
//		Student student = new Student();
//		student.setStudentId(102);
//		student.setStudentName("Pranav Mahajan");
//		student.setAbout("I am a Software Developer");
//
//		//add laptop
//		Laptop laptop = new Laptop();
//		laptop.setLaptopId(1020);
//		laptop.setModelNumber("dell0102");
//		laptop.setBrand("Dell");
//
//		laptop.setStudent(student);
//
//		student.setLaptop(laptop);
//
//		//save student
//		Student save = studentRepo.save(student);
//
//		logger.info("save student : {}",save.getStudentName());
//
//		Student stud1 = studentRepo.findById(102).get();
//		logger.info("name is : {}",stud1.getStudentName());
//
//		Laptop lapt1 = stud1.getLaptop();
//		logger.info("Laptop {} {} ",lapt1.getBrand(),lapt1.getModelNumber());
//
//	}


//	************************************************************************************************************


//	//OneToMany
//	@Override
//	public void run(String... args) throws Exception {
//
//		//add student
//		Student student = new Student();
//		student.setStudentId(101);
//		student.setStudentName("Pranav Mahajan");
//		student.setAbout("I am a Software Developer");
//
//		Address a1 = new Address();
//		a1.setAddressId(1101);
//		a1.setStreet("raver road");
//		a1.setCity("savda");
//		a1.setCountry("india");
//		a1.setStudent(student);
//
//		Address a2 = new Address();
//		a2.setAddressId(2101);
//		a2.setStreet("faizpur road");
//		a2.setCity("bhusaval");
//		a2.setCountry("india");
//		a2.setStudent(student);
//
//		List<Address> addressList = new ArrayList<>();
//		addressList.add(a1);
//		addressList.add(a2);
//
//		student.setAddressList(addressList);
//
//		Student save = studentRepo.save(student);
//		logger.info("student created : with address");
//	}


//	************************************************************************************************************


	//ManyToMany
	@Override
	public void run(String... args) throws Exception {

//		//adding product
//		Product p1 = new Product();
//		p1.setpId("p101");
//		p1.setProductName("IPhone 16 pro max");
//
//		Product p2 = new Product();
//		p2.setpId("p102");
//		p2.setProductName("IPhone 12 pro");
//
//		Product p3 = new Product();
//		p3.setpId("p103");
//		p3.setProductName("Samsung Tv");
//
//
//		//adding category
//		Category c1 = new Category();
//		c1.setcId("c1011");
//		c1.setTitle("Electronic");
//
//		Category c2 = new Category();
//		c2.setcId("c1012");
//		c2.setTitle("TV");
//
//		Category c3 = new Category();
//		c3.setcId("c1013");
//		c3.setTitle("Mobile");
//
//
//		//category me product add process
//		List<Product> products1 = c1.getProducts();
//		products1.add(p1);
//		products1.add(p2);
//		products1.add(p3);
//
//		List<Product> products2 = c2.getProducts();
//		products2.add(p3);
//
//		List<Product> products3 = c3.getProducts();
//		products3.add(p1);
//		products3.add(p2);
//
//		categoryRepo.save(c1);
//		categoryRepo.save(c2);
//		categoryRepo.save(c3);


		//**************************************************************************************************

		Category category1 = categoryRepo.findById("c1011").get();
		System.out.println("Category 1011 size : "+category1.getProducts().size());

		Category category2 = categoryRepo.findById("c1012").get();
		System.out.println("Category 1012 size : "+category2.getProducts().size());

		Category category3 = categoryRepo.findById("c1013").get();
		System.out.println("Category 1013 size : "+category3.getProducts().size());

		//**************************************************************************************************

		Product product1 = productRepo.findById("p101").get();
		System.out.println("Product 101 size : "+product1.getCategories().size());

		Product product2 = productRepo.findById("p102").get();
		System.out.println("Product 102 size : "+product2.getCategories().size());

		Product product3 = productRepo.findById("p103").get();
		System.out.println("Product 103 size : "+product3.getCategories().size());

	}

}
