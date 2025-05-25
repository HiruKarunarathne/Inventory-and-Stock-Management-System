//package com.intellij.InventoryAndStockManagementSystem.service;
//
//import com.intellij.InventoryAndStockManagementSystem.model.Item;
//import com.intellij.InventoryAndStockManagementSystem.util.ItemFileUtil;
//import java.util.*;
//
//public class ItemService {
//    private List<Item> items;
//
//
//
//    public ItemService() {
//        items = ItemFileUtil.readItems();
//    }
//
//    // Create
//    public void addItem(Item item) {
//        items.add(item);
//        ItemFileUtil.writeItems(items);
//    }
//
//    // Read
//    public List<Item> viewItems() {
//        return items;
//
//    }
//
//}
package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.Item;
import com.intellij.InventoryAndStockManagementSystem.util.ItemFileUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
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

    // ✅ Update
    public boolean updateItem(Item updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(updatedItem.getId())) {
                items.set(i, updatedItem);
                ItemFileUtil.writeItems(items);
                return true;
            }
        }
        return false;
    }

    // ✅ Delete
    public boolean deleteItem(String id) {
        boolean removed = items.removeIf(item -> item.getId().equals(id));
        if (removed) {
            ItemFileUtil.writeItems(items);
        }
        return removed;
    }
}
