package com.multiple.db.mysql.repo;

import com.multiple.db.mysql.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
