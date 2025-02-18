package com.jgiga.SpringSecurity.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgiga.SpringSecurity.models.Users;
import com.jgiga.SpringSecurity.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // Used for new users
    @PostMapping("/auth/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);

    }

    // Used for already registered users
    @PostMapping("/auth/login")
    public String login(@RequestBody Users user) {
        return userService.verify(user);
    }
}
