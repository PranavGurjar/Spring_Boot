package com.postgres;

import com.postgres.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostgreSqlTestApplication implements CommandLineRunner {

	@Autowired
	private StudentDao studentDao;

	public static void main(String[] args) {
		SpringApplication.run(PostgreSqlTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		this.studentDao.createTable();
//		this.studentDao.insertTable("Pranav Mahajan", "Jalgaon");
//		this.studentDao.updateTable(1, "Ram Mahajan", "Ayodhya");
		this.studentDao.deleteTable(1);
	}
}
