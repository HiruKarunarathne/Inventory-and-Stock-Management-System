package com.intellij.InventoryAndStockManagementSystem.model;

import java.time.LocalDate;

public abstract class BaseItem {
    private String id;
    private String name;
    private int quantity;
    private LocalDate date;

    public BaseItem() {}

    public BaseItem(String id, String name, int quantity, LocalDate date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.date = date;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public abstract String getType();
}