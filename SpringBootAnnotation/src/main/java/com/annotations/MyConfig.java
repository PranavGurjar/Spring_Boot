package com.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

@Configuration                                //2
@ComponentScan(basePackages = {"mypack"})     //6
public class MyConfig {

    //3
    @Bean("student1")
    @Lazy                  //8
    public Student getStudent(){
        System.out.println("Creating 1st student object...");
        return new Student("Student1");
    }

    @Bean("student2")
    @Lazy
    public Student createStudent(){
        System.out.println("Creating 2nd student object...");
        return new Student("Student2");
    }

    @Bean
    public Date getDate(){
        System.out.println("Creating new Date");
        return new Date();
    }

}
