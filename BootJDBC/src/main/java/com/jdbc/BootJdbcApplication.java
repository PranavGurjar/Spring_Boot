package com.jdbc;

import com.jdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class BootJdbcApplication implements CommandLineRunner {

	@Autowired
	private UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(BootJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.userDao.createTable());

		this.createUser();
//		this.updateUser();
//		this.deleteUser();
	}

	public void createUser() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter user Id: ");
		int userId = Integer.parseInt(br.readLine());

		System.out.println("Enter user name: ");
		String name = br.readLine();

		System.out.println("Enter user age: ");
		int age = Integer.parseInt(br.readLine());

		System.out.println("Enter user city: ");
		String city = br.readLine();

		int i = this.userDao.insertUser(userId, name, age, city);

		System.out.println(i + " user added!");
	}

	public void updateUser() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter user Id to update: ");
		int id = Integer.parseInt(br.readLine());

		System.out.println("Enter new user name: ");
		String name = br.readLine();

		System.out.println("Enter new user age: ");
		int age = Integer.parseInt(br.readLine());

		System.out.println("Enter new user city: ");
		String city = br.readLine();

		int i = this.userDao.updateUser(id, name, age, city);

		System.out.println(i + " user updated!");
	}



	//delete user
	public void deleteUser() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter user Id to delete: ");
		int id = Integer.parseInt(br.readLine());

		int i = this.userDao.deleteUser(id);

		System.out.println(i + " user deleted!");
	}

}
