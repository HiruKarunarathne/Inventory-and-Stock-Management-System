package com.intellij.InventoryAndStockManagementSystem.service.impl;

import com.intellij.InventoryAndStockManagementSystem.model.ManageUser;
import com.intellij.InventoryAndStockManagementSystem.service.ManageUserService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManageUserServiceImpl implements ManageUserService {

    private static final String FILE_PATH = "users.txt";

    @Override
    public void saveUser(ManageUser user) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(user.getName() + "," + user.getPhone() + "," + user.getEmail() + "," +
                    user.getUsername() + "," + user.getPassword() + "," + user.getImage() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ManageUser> getAllUsers() {
        List<ManageUser> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6);
                if (parts.length == 6) {
                    users.add(new ManageUser(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

}
