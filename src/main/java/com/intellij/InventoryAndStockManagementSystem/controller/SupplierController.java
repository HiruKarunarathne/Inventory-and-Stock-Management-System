package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.Supplier;
import com.intellij.InventoryAndStockManagementSystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Loads the HTML page
    @GetMapping("/addSupplier")
    public String viewPage(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliersSorted());
        return "addSupplier";
    }

    // AJAX POST handler for frontend form
    @PostMapping("/addSupplier")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addSupplierAjax(@RequestParam String id,
                                                  @RequestParam String name,
                                                  @RequestParam String address) {
        supplierService.addSupplier(new Supplier(id, name, address));
        return ResponseEntity.ok("Supplier added successfully");
    }

    // Returns JSON list of all suppliers
    @GetMapping("/getSuppliers")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<Supplier> getSuppliers() {
        return supplierService.getAllSuppliersSorted();
    }
}
