package com.jdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDao(){

    }

    //create table
    public int createTable(){
        String query = "CREATE TABLE IF NOT EXISTS USER(id int primary key, name varchar(200), age int, city varchar(200))";
        int update = this.jdbcTemplate.update(query);
        return update;
    }

    //inserting user to database
    public int insertUser(Integer id, String name, Integer age, String city){
        String query = "insert into user(id, name, age, city) values(?, ?, ?, ?)";
        int update = this.jdbcTemplate.update(query, new Object[] { id, name, age, city });
        return update;
    }

    // Update user
    public int updateUser(Integer id, String name, Integer age, String city) {
        String query = "UPDATE user SET name = ?, age = ?, city = ? WHERE id = ?";
        return jdbcTemplate.update(query, name, age, city, id);
    }

    // Delete user
    public int deleteUser(Integer id) {
        String query = "DELETE FROM user WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

}
