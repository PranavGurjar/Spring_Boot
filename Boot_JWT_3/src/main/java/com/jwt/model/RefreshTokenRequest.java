package com.jwt.model;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
