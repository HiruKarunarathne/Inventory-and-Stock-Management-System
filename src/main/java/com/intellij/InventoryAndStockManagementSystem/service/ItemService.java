package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.Item;
import com.intellij.InventoryAndStockManagementSystem.util.ItemFileUtil;
import java.util.*;

public class ItemService {
    private List<Item> items;



    public ItemService() {
        items = ItemFileUtil.readItems();
    }

    // Create
    public void addItem(Item item) {
        items.add(item);
        ItemFileUtil.writeItems(items);
    }

    // Read
    public List<Item> viewItems() {
        return items;

    }

}
