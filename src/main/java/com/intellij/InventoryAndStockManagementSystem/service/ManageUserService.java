package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.ManageUser;

import java.util.List;

public interface ManageUserService {
    void saveUser(ManageUser user);
    List<ManageUser> getAllUsers();
}
