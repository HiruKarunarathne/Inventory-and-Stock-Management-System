package com.example.Inventory.Management.System.User.Service;

import com.example.Inventory.Management.System.User.Model.User;

public interface UserService {
    User registerUser(User user);
    User loginUser(String username, String password);
    User getUserByUsername(String username);
    boolean hasRole(String username, String role);
}