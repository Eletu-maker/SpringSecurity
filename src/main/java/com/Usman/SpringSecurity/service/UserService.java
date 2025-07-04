package com.Usman.SpringSecurity.service;


import com.Usman.SpringSecurity.model.Users;
import com.Usman.SpringSecurity.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTServices jwtServices;

    @Autowired
    AuthenticationManager manager;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users users){
        users.setPassword(encoder.encode(users.getPassword()));
       return userRepo.save(users);
    }

    public String verify(String username, String password) {
        logger.info("Login attempt for user: {}", username);
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            logger.info("Login successful for user: {}", username);
            return jwtServices.generateToken(username);
        }
        logger.warn("Login failed for user: {}", username);
        return "fail";
    }
}
