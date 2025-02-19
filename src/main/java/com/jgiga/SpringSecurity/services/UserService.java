package com.jgiga.SpringSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jgiga.SpringSecurity.config.Auth.JWT.JWTService;
import com.jgiga.SpringSecurity.config.Auth.Roles.Roles;
import com.jgiga.SpringSecurity.models.Users;
import com.jgiga.SpringSecurity.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya está registrado");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Roles.USER);
        return userRepository.save(user);
    }

    public String verify(Users user) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }
}