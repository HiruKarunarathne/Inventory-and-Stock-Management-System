package com.intellij.InventoryAndStockManagementSystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageService {
    @Value("${file.storage.path}")
    private String storagePath;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> List<T> readFromFile(String filename, Class<T> type) {
        try {
            File file = new File(storagePath + filename);
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from file: " + filename, e);
        }
    }

    public <T> void writeToFile(String filename, List<T> data) {
        try {
            File file = new File(storagePath + filename);
            objectMapper.writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: " + filename, e);
        }
    }
}
