package com.intellij.InventoryAndStockManagementSystem.model;

import java.time.LocalDate;

public class Transaction extends BaseItem {
    private double price;
    private String type;

    public Transaction() {
        super();
    }

    public Transaction(String id, String itemName, int quantity, double price, LocalDate date, String type) {
        super(id, itemName, quantity, date);
        this.price = price;
        this.type = type;
    }

    // Getters and Setters
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getQuantity() + "," + price + "," + getDate() + "," + type;
    }
}