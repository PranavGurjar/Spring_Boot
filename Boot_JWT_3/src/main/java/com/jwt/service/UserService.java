package com.jwt.service;

import com.jwt.entities.User;
import com.jwt.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    public List<User> users = new ArrayList<>();

//    public UserService() {
//        users.add(new User(UUID.randomUUID().toString(), "Pranav Mahajan", "prm@gmail.com"));
//        users.add(new User(UUID.randomUUID().toString(), "Yash Mahajan", "ygm@gmail.com"));
//        users.add(new User(UUID.randomUUID().toString(), "Kunal Mahajan", "ksm@gmail.com"));
//        users.add(new User(UUID.randomUUID().toString(), "Sujal Mahajan", "svm@gmail.com"));
//    }

    public List<User> getUsers() {

        return userRepo.findAll();

    }

    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}
