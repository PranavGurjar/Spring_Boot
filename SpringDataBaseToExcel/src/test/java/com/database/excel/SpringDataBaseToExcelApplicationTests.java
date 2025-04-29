package com.database.excel;

import com.database.excel.repo.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataBaseToExcelApplicationTests {
	@Autowired
	private CategoryRepo categoryRepo;

	@Test
	public void testCategoryRepo(){
		System.out.println("Test Started!!");
		categoryRepo.findAll().forEach(category -> System.out.println(category.getTitle()));
	}

}
