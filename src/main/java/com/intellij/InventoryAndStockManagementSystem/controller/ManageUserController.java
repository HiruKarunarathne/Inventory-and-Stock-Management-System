package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.ManageUser;
import com.intellij.InventoryAndStockManagementSystem.service.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller for managing user operations
@RestController
@RequestMapping("/manageusers")
@CrossOrigin(origins = "*")
public class ManageUserController {

    @Autowired
    private ManageUserService manageUserService;

    // Retrieves all users
    @GetMapping
    public List<ManageUser> getAllUsers() {
        return manageUserService.getAllUsers();
    }

    // Retrieves a user by their ID
    @GetMapping("/{id}")
    public ResponseEntity<ManageUser> getUserById(@PathVariable String id) {
        ManageUser user = manageUserService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    // Adds a new user
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody ManageUser user) {
        try {
            manageUserService.saveUser(user);
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding user");
        }
    }

    // Updates an existing user
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody ManageUser user) {
        try {
            manageUserService.updateUser(user);
            return ResponseEntity.ok("User updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user.");
        }
    }

    // Deletes a user by their ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        try {
            manageUserService.deleteUserById(id);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting user.");
        }
    }
}
