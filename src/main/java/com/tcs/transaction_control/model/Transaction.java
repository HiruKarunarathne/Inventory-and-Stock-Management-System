package com.tcs.transaction_control.model;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class Transaction {
    private int id;
    private String itemName;
    private int quantity;
    private double price;
    private LocalDate date;
    private String type;

    public Transaction() {}

    public Transaction(int id, String itemName, int quantity, double price, LocalDate date, String type) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.type = type;
    }

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

    @Override
    public String toString() {
        return id + "," + itemName + "," + quantity + "," + price + "," + date + "," + type;
    }
}