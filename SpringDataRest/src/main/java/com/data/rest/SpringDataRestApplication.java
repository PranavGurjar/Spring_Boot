package com.data.rest;

import com.data.rest.entity.Book;
import com.data.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataRestApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Book book1 = new Book();
//		book1.setTitle("C lang");
//		book1.setContent("Content of C");
//
//		Book book2 = new Book();
//		book2.setTitle("C++ lang");
//		book2.setContent("Content of C++");
//
//		this.bookRepository.save(book1);
//		this.bookRepository.save(book2);
	}
}
