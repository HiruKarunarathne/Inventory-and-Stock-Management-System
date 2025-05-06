package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.Item;
import com.intellij.InventoryAndStockManagementSystem.service.ItemService;
import com.intellij.InventoryAndStockManagementSystem.util.SortUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService = new ItemService();

    // Create (Add)
    @PostMapping("/add")
    public String addItem(@RequestBody Item item) {
        itemService.addItem(item);
        return "Item added!";
    }

    // Read (View)
    @GetMapping("/view")
    public List<Item> viewItems() {
        return itemService.viewItems();
    }

    // (Optional) Sort by expiry date
    @GetMapping("/sorted")
    public List<Item> getSortedItems() {
        return SortUtil.mergeSortByExpiryDate(itemService.viewItems());
    }

}
