package com.jgiga.SpringSecurity.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgiga.SpringSecurity.config.Auth.JWT.TokenResponse;
import com.jgiga.SpringSecurity.models.Users;
import com.jgiga.SpringSecurity.services.UserService;
import com.jgiga.SpringSecurity.utils.ErrorResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // Used for new users
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        try {
            String token = userService.register(user);
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage()));
        }
    }

    // Used for already registered users
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        try {
            String token = userService.verify(user);
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage()));
        }
    }
}
