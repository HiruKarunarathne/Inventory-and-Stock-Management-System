package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.Item;
import com.intellij.InventoryAndStockManagementSystem.util.ItemFileUtil;
import java.util.*;

public class ItemService {
    private List<Item> items;

    public ItemService() {
        items = ItemFileUtil.readItems();
    }
    public void addItem(Item item) {
        items.add(item);
        ItemFileUtil.writeItems(items);
    }

    public List<Item> viewItems() {
        return items;
    }

    public void updateItem(String id, Item updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.set(i, updatedItem);
                break;
            }
        }
        ItemFileUtil.writeItems(items);
    }

    public void deleteItem(String id) {
        items.removeIf(item -> item.getId().equals(id));
        ItemFileUtil.writeItems(items);
    }
}