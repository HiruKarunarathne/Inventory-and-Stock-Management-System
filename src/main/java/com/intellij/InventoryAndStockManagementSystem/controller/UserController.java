package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.User;
import com.intellij.InventoryAndStockManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    //Register user (passing full User object)
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    //Login user (passing username & password separately from User object)
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return userService.loginUser(user.getUsername(), user.getPassword());
    }

    // Admin role-based access control
    @GetMapping("/admin/dashboard")
    public String adminDashboard(@RequestParam String username) {
        if (userService.hasRole(username, "Admin")) {
            return "Welcome to Admin Dashboard";
        } else {
            return "Access Denied!";
        }
    }
}
