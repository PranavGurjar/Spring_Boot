package com.security.repo;

import com.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //username, it will return the user of given username
    public User findByUsername(String username);


}
