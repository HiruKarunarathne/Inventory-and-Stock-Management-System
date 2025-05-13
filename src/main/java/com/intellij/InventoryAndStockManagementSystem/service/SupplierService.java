package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.Supplier;
import java.util.List;

public interface SupplierService {
    void addSupplier(Supplier supplier);
    List<Supplier> getAllSuppliersSorted();
}
