package com.jgiga.SpringSecurity.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloController {
    
    @GetMapping("/")
    public String getHelloString (HttpServletRequest httpServletRequest) {
        return "Hi - "+ httpServletRequest.getSession().getId();
    }

}
