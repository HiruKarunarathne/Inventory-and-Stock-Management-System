package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.User;

public interface UserService {
    User registerUser(User user);
    User loginUser(String username, String password);
    User getUserByUsername(String username);
    boolean hasRole(String username, String role);
}
