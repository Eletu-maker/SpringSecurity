package com.Usman.SpringSecurity.controller;

import com.Usman.SpringSecurity.DTO.request.LoginRequest;
import com.Usman.SpringSecurity.model.Users;

import com.Usman.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users users){
        return  userService.register(users);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.verify(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
