package com.intellij.InventoryAndStockManagementSystem.service;



import com.intellij.InventoryAndStockManagementSystem.model.User;

public interface UserService {
    boolean register(User user);
    User login(String username, String password);
    User findByUsername(String username);
}
