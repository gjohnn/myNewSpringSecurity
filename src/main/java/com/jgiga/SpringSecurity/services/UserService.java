package com.jgiga.SpringSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jgiga.SpringSecurity.models.Users;
import com.jgiga.SpringSecurity.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya está registrado");
        }
    
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
