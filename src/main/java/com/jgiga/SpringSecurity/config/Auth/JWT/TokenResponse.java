package com.jgiga.SpringSecurity.config.Auth.JWT;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenResponse {
    private String token;
}
