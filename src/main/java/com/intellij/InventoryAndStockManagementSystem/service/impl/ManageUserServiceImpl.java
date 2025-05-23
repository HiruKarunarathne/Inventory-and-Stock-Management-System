package com.intellij.InventoryAndStockManagementSystem.service.impl;

import com.intellij.InventoryAndStockManagementSystem.model.ManageUser;
import com.intellij.InventoryAndStockManagementSystem.service.ManageUserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

@Service
public class ManageUserServiceImpl implements ManageUserService {

    private final String FILE_PATH = "manageusers.txt";  // separate file for manage users

    private Map<String, ManageUser> userMap = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        // load users from file
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Format: id,name,phone,email,username,password,role
                String[] parts = line.split(",", 7);
                if (parts.length == 7) {
                    ManageUser user = new ManageUser(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                    userMap.put(user.getId(), user);
                }
            }
        } catch (IOException e) {
            System.out.println("ManageUser file not found or error reading");
        }
    }

    @Override
    public List<ManageUser> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public ManageUser getUserById(String id) {
        return userMap.get(id);
    }

    @Override
    public void saveUser(ManageUser user) {
        // generate unique ID if not present
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        userMap.put(user.getId(), user);
        saveToFile();
    }

    @Override
    public void updateUser(ManageUser user) {
        if (user.getId() != null && userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user);
            saveToFile();
        } else {
            throw new RuntimeException("User not found for update");
        }
    }

    @Override
    public void deleteUserById(String id) {
        userMap.remove(id);
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (ManageUser user : userMap.values()) {
                bw.write(String.join(",",
                        user.getId(),
                        user.getName(),
                        user.getPhone(),
                        user.getEmail(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getRole()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
