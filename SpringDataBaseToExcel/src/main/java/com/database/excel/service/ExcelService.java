package com.database.excel.service;

import com.database.excel.entities.Category;
import com.database.excel.helper.Helper;
import com.database.excel.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    private CategoryRepo categoryRepo;

    public ByteArrayInputStream getActualData() throws IOException {
        List<Category> categoryList = categoryRepo.findAll();

        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(categoryList);
        return byteArrayInputStream;
    }
}
