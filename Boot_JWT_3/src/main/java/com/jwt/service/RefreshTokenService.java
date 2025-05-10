package com.jwt.service;

import com.jwt.entities.RefreshToken;
import com.jwt.entities.User;
import com.jwt.repo.RefreshTokenRepo;
import com.jwt.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    public long refreshTokenValidity=2*60*1000;

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;

    @Autowired
    private UserRepo userRepo;


    public RefreshToken createRefreshToken(String userName){

        User user = userRepo.findByEmail(userName).get();
        RefreshToken refreshToken1 = user.getRefreshToken();

        if (refreshToken1==null){
            refreshToken1 = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expiry(Instant.now().plusMillis(refreshTokenValidity))
                    .user(userRepo.findByEmail(userName).get())
                    .build();
        }else {
            refreshToken1.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
        }

        user.setRefreshToken(refreshToken1);

        //save to database
        refreshTokenRepo.save(refreshToken1);

        return refreshToken1;
    }

    public RefreshToken verifyRefreshToken(String refreshToken){

        RefreshToken refreshTokenOb = refreshTokenRepo.findByRefreshToken(refreshToken).orElseThrow(() -> new RuntimeException("Given Token Doesn't Exists In DB!"));

        if (refreshTokenOb.getExpiry().compareTo(Instant.now())<0){
            refreshTokenRepo.delete(refreshTokenOb);
            throw new RuntimeException("Refresh Token Expired!!");
        }

        return refreshTokenOb;
    }
}
