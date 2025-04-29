package com.database.excel;

import com.database.excel.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataBaseToExcelApplication implements CommandLineRunner {
	@Autowired
	private CategoryRepo categoryRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataBaseToExcelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Test Started!!");
//		categoryRepo.findAll().forEach(category -> System.out.println(category.getTitle()));
	}
}
