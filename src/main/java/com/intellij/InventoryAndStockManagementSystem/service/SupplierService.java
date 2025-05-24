package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.Supplier;
import java.util.List;

// Service interface for supplier-related operations
public interface SupplierService {
    // Adds a new supplier
    void addSupplier(Supplier supplier);
    // Retrieves all suppliers, sorted
    List<Supplier> getAllSuppliersSorted();
}
