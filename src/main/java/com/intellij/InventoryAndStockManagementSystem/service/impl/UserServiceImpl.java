package com.intellij.InventoryAndStockManagementSystem.service.impl;

import com.intellij.InventoryAndStockManagementSystem.model.ManageUser;
import com.intellij.InventoryAndStockManagementSystem.model.User;
import com.intellij.InventoryAndStockManagementSystem.service.ManageUserService;
import com.intellij.InventoryAndStockManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private Map<String, User> userMap = new HashMap<>();

    @Autowired
    private ManageUserService manageUserService;

    @PostConstruct
    public void init() {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6); // 7 fields now including role
                if (parts.length == 6) {
                    User user = new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    userMap.put(user.getUsername(), user);
                }
            }
        } catch (IOException e) {
            System.out.println("User file not found or error reading users.txt");
        }
    }

    @Override
    public boolean register(User user) {
        if (userMap.containsKey(user.getUsername())) {
            return false;
        }
        if (!isValidPassword(user.getPassword())) {
            return false;
        }
        userMap.put(user.getUsername(), user);
        saveUserToFile(user);

        ManageUser mUser = new ManageUser(
                UUID.randomUUID().toString(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
        manageUserService.saveUser(mUser);

        return true;
    }

    private void saveUserToFile(User user) {
        try (FileWriter fw = new FileWriter("users.txt", true)) {
            fw.write(user.getName() + "," + user.getPhone() + "," + user.getEmail() + "," +
                    user.getUsername() + "," + user.getPassword() + "," + user.getRole() +"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User login(String username, String password) {
        User user = userMap.get(username);
        return (user != null && user.getPassword().equals(password)) ? user : null;
    }

    @Override
    public User findByUsername(String username) {
        return userMap.get(username);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*");
    }

}
