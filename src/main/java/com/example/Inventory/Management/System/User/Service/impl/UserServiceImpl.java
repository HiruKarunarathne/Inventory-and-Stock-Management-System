package com.example.Inventory.Management.System.User.Service.impl;

import com.example.Inventory.Management.System.User.Model.User;
import com.example.Inventory.Management.System.User.Repository.UserRepository;
import com.example.Inventory.Management.System.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // tells Spring this is a service class
public class UserServiceImpl implements UserService {

    @Autowired  // Spring will automatically inject the repository
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        userRepository.saveUser(user); // Saves to file
        return user; // Return the same user object
    }


    @Override
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // login successful
        }
        return null; // login failed
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //method to check role
    @Override
    public boolean hasRole(String username, String role) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getRole().equalsIgnoreCase(role)) {
            return true;
        }
        return false;
    }
}
