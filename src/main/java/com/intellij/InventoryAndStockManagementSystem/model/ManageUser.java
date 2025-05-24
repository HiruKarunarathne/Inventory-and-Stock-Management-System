package com.intellij.InventoryAndStockManagementSystem.model;

public class ManageUser {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String role;

    public ManageUser() {}

    public ManageUser(String id, String name, String phone, String email, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}