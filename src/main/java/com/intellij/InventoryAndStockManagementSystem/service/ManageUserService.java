package com.intellij.InventoryAndStockManagementSystem.service;


import com.intellij.InventoryAndStockManagementSystem.model.ManageUser;
import java.util.List;

public interface ManageUserService {
    List<ManageUser> getAllUsers();
    ManageUser getUserById(String id);
    void saveUser(ManageUser user);
    void updateUser(ManageUser user);
    void deleteUserById(String id);
}
