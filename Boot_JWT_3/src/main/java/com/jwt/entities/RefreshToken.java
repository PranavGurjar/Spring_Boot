package com.jwt.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;

    private String refreshToken;

    private Instant expiry;

    @OneToOne
    private User user;

    public RefreshToken() {
    }

    public RefreshToken(int tokenId, String refreshToken, Instant expiry, User user) {
        this.tokenId = tokenId;
        this.refreshToken = refreshToken;
        this.expiry = expiry;
        this.user = user;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Instant getExpiry() {
        return expiry;
    }

    public void setExpiry(Instant expiry) {
        this.expiry = expiry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "tokenId=" + tokenId +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiry=" + expiry +
                ", user=" + user +
                '}';
    }
}
