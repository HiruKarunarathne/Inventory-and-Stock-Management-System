package com.intellij.InventoryAndStockManagementSystem.service;



import com.intellij.InventoryAndStockManagementSystem.model.User;

// Service interface for user-related operations
public interface UserService {
    // Registers a new user
    boolean register(User user);
    // Logs in a user
    User login(String username, String password);
    // Finds a user by their username
    User findByUsername(String username);
}
