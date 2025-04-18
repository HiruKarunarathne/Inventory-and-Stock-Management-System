package com.intellij.InventoryAndStockManagementSystem.model;

public class Item {
    private String id;
    private String name;
    private int quantity;
    private String expiryDate; // Format: YYYY-MM-DD

    // Constructors, getters, setters

    public Item(String id, String name, int quantity, String expiryDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

// Getters and setters...
// Getter for id
public String getId() {
    return id;
}

    // Setter for id (optional)
    public void setId(String id) {
        this.id = id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for quantity
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter and setter for expiryDate
    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

}
