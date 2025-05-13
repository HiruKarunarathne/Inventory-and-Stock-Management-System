package com.intellij.InventoryAndStockManagementSystem.controller;


import com.intellij.InventoryAndStockManagementSystem.model.Supplier;
import com.intellij.InventoryAndStockManagementSystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/addSupplier")
    public String viewPage(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliersSorted());
        return "addSupplier";
    }

    @PostMapping("/addSupplier")
    public String addSupplier(@RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String address,
                              Model model) {
        supplierService.addSupplier(new Supplier(id, name, address));
        model.addAttribute("suppliers", supplierService.getAllSuppliersSorted());
        return "addSupplier";
    }
}



