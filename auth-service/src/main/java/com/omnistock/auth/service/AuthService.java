package com.omnistock.auth.service;

import org.springframework.stereotype.Service;

import com.omnistock.auth.entity.User;
import com.omnistock.auth.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public User register(User user) {

        User existingUser =
                userRepository.findByUsername(user.getUsername());

        if (existingUser != null) {
            return null;
        }

        return userRepository.save(user);
    }

    public String login(String username, String password) {

        User user =
                userRepository.findByUsername(username);

        if (user != null &&
            user.getPassword().equals(password)) {

            return jwtService.generateToken(username);
        }

        return null;
    }
}