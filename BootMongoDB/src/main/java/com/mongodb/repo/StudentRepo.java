package com.mongodb.repo;

import com.mongodb.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, Integer> {

}
