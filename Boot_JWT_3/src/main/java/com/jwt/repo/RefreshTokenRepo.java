package com.jwt.repo;

import com.jwt.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, String> {

    //custom method
    Optional<RefreshToken> findByRefreshToken(String token);

}
