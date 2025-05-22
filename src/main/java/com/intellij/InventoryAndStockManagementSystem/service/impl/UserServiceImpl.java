package com.intellij.InventoryAndStockManagementSystem.service.impl;

public class UserServiceImpl {
}

//import com.intellij.InventoryAndStockManagementSystem.model.User;
//import com.intellij.InventoryAndStockManagementSystem.model.ManageUser;
//import com.intellij.InventoryAndStockManagementSystem.service.ManageUserService;
//import com.intellij.InventoryAndStockManagementSystem.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    private Map<String, User> userMap = new HashMap<>();
//
//    @Autowired
//    private ManageUserService manageUserService; // ✅ Injected here
//
//    @Override
//    public boolean register(User user) {
//        if (userMap.containsKey(user.getUsername())) {
//            return false;
//        }
//        if (!isValidPassword(user.getPassword())) {
//            return false;
//        }
//        userMap.put(user.getUsername(), user);
//        saveUserToFile(user);
//
//        // ✅ Save to ManageUser list for frontend table
//        ManageUser mUser = new ManageUser(
//                user.getName(),
//                user.getPhone(),
//                user.getEmail(),
//                user.getUsername(),
//                user.getPassword(),
//                user.getImage()
//        );
//        manageUserService.saveUser(mUser);
//
//        return true;
//    }
//
//    private void saveUserToFile(User user) {
//        try (FileWriter fw = new FileWriter("users.txt", true)) {
//            fw.write(user.getName() + "," + user.getPhone() + "," + user.getEmail() + "," +
//                    user.getUsername() + "," + user.getPassword() + "," + user.getImage() + "\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public User login(String username, String password) {
//        User user = userMap.get(username);
//        return (user != null && user.getPassword().equals(password)) ? user : null;
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        return userMap.get(username);
//    }
//
//    private boolean isValidPassword(String password) {
//        return password.length() >= 8 && password.matches(".*[A-Z].*");
//    }
//}


