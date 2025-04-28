package com.intellij.InventoryAndStockManagementSystem.util;


import com.intellij.InventoryAndStockManagementSystem.model.Item;
import java.io.*;
import java.util.*;

public class ItemFileUtil {
    private static final String FILE_NAME = "items.txt";

    public static List<Item> readItems() {
        List<Item> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                items.add(new Item(data[0], data[1], Integer.parseInt(data[2]), data[3]));
            }
        } catch (IOException e) {
            // File might not exist yet
        }
        return items;
    }

    public static void writeItems(List<Item> items) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Item item : items) {
                bw.write(item.getId() + "," + item.getName() + "," + item.getQuantity() + "," + item.getExpiryDate());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}