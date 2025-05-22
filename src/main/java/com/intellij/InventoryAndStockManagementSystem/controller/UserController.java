package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.User;
import com.intellij.InventoryAndStockManagementSystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true") // <-- change origin to your frontend URL
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        boolean success = userService.register(user);
        return success ? "SUCCESS" : "FAILED";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest, HttpSession session) {
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            session.setAttribute("loggedUser", user);
            return "SUCCESS";
        }
        return "FAILED";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "LOGGED_OUT";
    }

    @GetMapping("/current")
    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("loggedUser");
    }
}
