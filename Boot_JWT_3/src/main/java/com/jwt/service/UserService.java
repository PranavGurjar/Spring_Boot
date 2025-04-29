package com.jwt.service;

import com.jwt.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    public List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(UUID.randomUUID().toString(), "Pranav Mahajan", "prm@gmail.com"));
        users.add(new User(UUID.randomUUID().toString(), "Yash Mahajan", "ygm@gmail.com"));
        users.add(new User(UUID.randomUUID().toString(), "Kunal Mahajan", "ksm@gmail.com"));
        users.add(new User(UUID.randomUUID().toString(), "Sujal Mahajan", "svm@gmail.com"));
    }

    public List<User> getUsers() {
        return this.users;
    }
}
