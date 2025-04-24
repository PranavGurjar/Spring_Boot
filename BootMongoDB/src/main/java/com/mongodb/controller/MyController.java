package com.mongodb.controller;

import com.mongodb.model.Student;
import com.mongodb.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class MyController {
    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        Student save = this.studentRepo.save(student);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/")
    public ResponseEntity<?> getStudents(){
        return ResponseEntity.ok(this.studentRepo.findAll());
    }
}
