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
}
