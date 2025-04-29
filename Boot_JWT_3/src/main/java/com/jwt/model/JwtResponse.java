package com.jwt.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder  // <--- This enables builder()
public class JwtResponse {
    private String jwtToken;
    private String username;
}
