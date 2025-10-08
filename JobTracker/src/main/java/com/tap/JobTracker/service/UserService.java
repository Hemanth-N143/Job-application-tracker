package com.tap.JobTracker.service;


import com.tap.JobTracker.model.User;
import com.tap.JobTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public boolean registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return false; // Email already exists
        }
        userRepository.save(user);
        return true; // Successfully registered
    }


    // Find user by email (for login)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

