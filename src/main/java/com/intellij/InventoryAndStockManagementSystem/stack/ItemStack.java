package com.intellij.InventoryAndStockManagementSystem.stack;

import com.intellij.InventoryAndStockManagementSystem.model.Item;

public class ItemStack {
    private Item[] items;
    private int top;
    private final int MAX_SIZE = 100; // Max items allowed

    public ItemStack() {
        items = new Item[MAX_SIZE];
        top = -1;
    }

    // ✅ Push
    public void push(Item item) {
        if (top >= MAX_SIZE - 1) {
            throw new StackOverflowError("Stack is full");
        }
        items[++top] = item;
    }

    // ✅ Pop
    public Item pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items[top--];
    }

    // ✅ Peek
    public Item peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items[top];
    }

    // ✅ isEmpty
    public boolean isEmpty() {
        return top == -1;
    }

    // ✅ Get All Items
    public Item[] getAllItems() {
        Item[] currentItems = new Item[top + 1];
        System.arraycopy(items, 0, currentItems, 0, top + 1);
        return currentItems;
    }

    // ✅ Update Item by ID
    public boolean updateItem(Item updatedItem) {
        boolean found = false;
        for (int i = 0; i <= top; i++) {
            if (items[i].getId().equals(updatedItem.getId())) {
                items[i] = updatedItem;
                found = true;
                break;
            }
        }
        return found;
    }

    // ✅ Delete Item by ID
    public boolean deleteItemById(String id) {
        boolean found = false;
        Item[] newItems = new Item[MAX_SIZE];
        int newTop = -1;

        for (int i = 0; i <= top; i++) {
            if (!items[i].getId().equals(id)) {
                newItems[++newTop] = items[i];
            } else {
                found = true;
            }
        }

        items = newItems;
        top = newTop;
        return found;
    }
}
