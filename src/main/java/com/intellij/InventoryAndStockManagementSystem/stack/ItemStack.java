package com.intellij.InventoryAndStockManagementSystem.stack;

import com.intellij.InventoryAndStockManagementSystem.model.Item;
import java.util.Stack;

public class ItemStack {
    private Stack<Item> stack = new Stack<>();

    public void push(Item item) {
        stack.push(item);
    }

    public Item pop() {
        return stack.pop();
    }
    public Item peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public Stack<Item> getAllItems() {
        return stack;
    }

    // ✅ Update item by ID
    public boolean updateItem(Item updatedItem) {
        Stack<Item> temp = new Stack<>();
        boolean found = false;

        while (!stack.isEmpty()) {
            Item item = stack.pop();
            if (item.getId().equals(updatedItem.getId())) {
                temp.push(updatedItem);
                found = true;
            } else {
                temp.push(item);
            }
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return found;
    }

    // ✅ Delete item by ID
    public boolean deleteItemById(String id) {
        Stack<Item> temp = new Stack<>();
        boolean found = false;

        while (!stack.isEmpty()) {
            Item item = stack.pop();
            if (item.getId().equals(id)) {
                found = true;
                continue;
            }
            temp.push(item);
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return found;
    }

}
