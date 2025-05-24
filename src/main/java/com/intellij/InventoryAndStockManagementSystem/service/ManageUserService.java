package com.intellij.InventoryAndStockManagementSystem.service;


import com.intellij.InventoryAndStockManagementSystem.model.ManageUser;
import java.util.List;

// Service interface for managing user operations
public interface ManageUserService {
    // Retrieves all users
    List<ManageUser> getAllUsers();
    // Retrieves a user by their ID
    ManageUser getUserById(String id);
    // Saves a new user
    void saveUser(ManageUser user);
    // Updates an existing user
    void updateUser(ManageUser user);
    // Deletes a user by their ID
    void deleteUserById(String id);
}
