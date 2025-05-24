package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.Item;
import com.intellij.InventoryAndStockManagementSystem.util.ItemFileUtil;
import java.util.*;

// Service for handling item-related operations
public class ItemService {
    private List<Item> items;


    // Constructor that loads items from file upon initialization
    public ItemService() {
        items = ItemFileUtil.readItems();
    }

    // Adds a new item
    // Create
    public void addItem(Item item) {
        items.add(item);
        ItemFileUtil.writeItems(items);
    }

    // Retrieves all items
    // Read
    public List<Item> viewItems() {
        return items;

    }

}
