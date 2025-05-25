package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.Supplier;
import com.intellij.InventoryAndStockManagementSystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;

import java.util.List;

// Controller for handling supplier-related requests
@Controller
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Loads the HTML page for adding a supplier
    @GetMapping("/addSupplier")
    public String viewPage(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliersSorted());
        return "addSupplier";
    }

    // Handles AJAX POST request from the frontend form to add a supplier
    @PostMapping("/addSupplier")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addSupplierAjax(@RequestParam String id,
                                                  @RequestParam String name,
                                                  @RequestParam String address) {
        supplierService.addSupplier(new Supplier(id, name, address));
        return ResponseEntity.ok("Supplier added successfully");
    }

    // Returns a JSON list of all suppliers
    @GetMapping("/getSuppliers")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<Supplier> getSuppliers() {
        return supplierService.getAllSuppliersSorted();
    }
    // ✅ Update Supplier
    @PostMapping("/updateSupplier")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateSupplier(@RequestParam String id,
                                                 @RequestParam String name,
                                                 @RequestParam String address) {
        boolean result = supplierService.updateSupplier(new Supplier(id, name, address));
        return result
                ? ResponseEntity.ok("Supplier updated successfully")
                : ResponseEntity.badRequest().body("Supplier not found");
    }

    // ✅ Delete Supplier
    @PostMapping("/deleteSupplier")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteSupplier(@RequestParam String id) {
        boolean result = supplierService.deleteSupplierById(id);
        return result
                ? ResponseEntity.ok("Supplier deleted successfully")
                : ResponseEntity.badRequest().body("Supplier not found");
    }

}
