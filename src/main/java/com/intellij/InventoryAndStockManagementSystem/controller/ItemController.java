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

    @PostMapping("/add")
    public String addItem(@RequestBody Item item) {
        itemService.addItem(item);
        return "Item added!";
    }

    @GetMapping("/view")
    public List<Item> viewItems() {
        return itemService.viewItems();
    }

    @PutMapping("/update/{id}")
    public String updateItem(@PathVariable String id, @RequestBody Item item) {
        itemService.updateItem(id, item);
        return "Item updated!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return "Item deleted!";
    }

    @GetMapping("/sorted")
    public List<Item> getSortedItems() {
        return SortUtil.mergeSortByExpiryDate(itemService.viewItems());
    }
}