package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.User;
import com.intellij.InventoryAndStockManagementSystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public ResponseEntity<String> login(@RequestBody User loginRequest, HttpServletRequest request) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body("Missing username or password");
        }
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            return ResponseEntity.ok("SUCCESS");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("FAILED");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return "LOGGED_OUT";
    }

    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (User) session.getAttribute("loggedUser") : null;
    }

}