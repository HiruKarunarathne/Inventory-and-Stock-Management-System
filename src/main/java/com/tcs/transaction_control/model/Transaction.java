package com.tcs.transaction_control.model;

import java.time.LocalDate;

public class Transaction {
    // Fields to store transaction details
    private int id;           // Unique ID for each transaction
    private String itemName;  // Name of the item involved in the transaction
    private int quantity;     // Quantity of items sold or restocked
    private double price;     // Total price of the transaction
    private LocalDate date;   // Date of the transaction
    private String type;      // Type of transaction: "Sale" or "Restock"

    // Default constructor (needed for Thymeleaf forms)
    public Transaction() {}

    // Constructor with all fields
    public Transaction(int id, String itemName, int quantity, double price, LocalDate date, String type) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.type = type;
    }

    // Getters and setters for each field
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    // Format the transaction as a string for file storage (CSV format)
    @Override
    public String toString() {
        return id + "," + itemName + "," + quantity + "," + price + "," + date + "," + type;
    }
}