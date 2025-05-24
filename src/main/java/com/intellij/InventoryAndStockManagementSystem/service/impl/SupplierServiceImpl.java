package com.intellij.InventoryAndStockManagementSystem.service.impl;

import com.intellij.InventoryAndStockManagementSystem.model.Supplier;
import com.intellij.InventoryAndStockManagementSystem.service.SupplierService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

// Implementation of the SupplierService interface
@Service
public class SupplierServiceImpl implements SupplierService {
    private static final String FILE_PATH = "suppliers.txt";

    // Adds a new supplier to the file
    @Override
    public void addSupplier(Supplier supplier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(supplier.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Retrieves all suppliers from the file, sorted by name
    @Override
    public List<Supplier> getAllSuppliersSorted() {
        List<Supplier> suppliers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                suppliers.add(Supplier.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(suppliers); // DSA Sorting by Name
        return suppliers;
    }
}


