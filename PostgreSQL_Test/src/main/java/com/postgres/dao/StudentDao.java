package com.postgres.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //create table
    public void createTable(){
        var query = "CREATE TABLE student(id SERIAL PRIMARY KEY, name VARCHAR(200) NOT NULL, city VARCHAR(200))";
        this.jdbcTemplate.update(query);
    }

    //inserting the data
    public void insertTable(String name, String city){
        String query = "INSERT INTO student( name, city) VALUES(?, ?)";
        int update = this.jdbcTemplate.update(query, name, city);
        System.out.println(update+" rows added!!");
    }

    // Update the data
    public void updateTable(Integer id, String name, String city) {
        String query = "UPDATE student SET name = ?, city = ? WHERE id = ?";
        int update = this.jdbcTemplate.update(query, name, city, id);
        System.out.println(update+" rows updated!!");
    }

    // Delete the data
    public void deleteTable(Integer id) {
        String query = "DELETE FROM student WHERE id = ?";
        int update = this.jdbcTemplate.update(query, id);
        System.out.println(update+" rows deleted!!");
    }

}
