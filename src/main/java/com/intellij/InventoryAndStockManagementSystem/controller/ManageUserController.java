
package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.ManageUser;
import com.intellij.InventoryAndStockManagementSystem.service.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class ManageUserController {

    @Autowired
    private ManageUserService manageUserService;

    @GetMapping
    public List<ManageUser> getAllUsers() {
        return manageUserService.getAllUsers();
    }
}
