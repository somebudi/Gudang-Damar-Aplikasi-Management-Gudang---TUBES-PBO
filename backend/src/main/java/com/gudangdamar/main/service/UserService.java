package com.gudangdamar.main.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudangdamar.main.model.User;
import com.gudangdamar.main.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method untuk autentikasi user
    public boolean authenticate(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println("User found: " + user.getUsername());
            return user.getPassword().equals(password);
        }
    
        System.out.println("User not found");
        return false;
    }
    
    
}

