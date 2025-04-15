package com.tcs.transaction_control.service;

import com.tcs.transaction_control.model.Transaction;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    // List to store all transactions in memory
    private final List<Transaction> transactions = new ArrayList<>();
    // File path for storing transactions
    private final String filePath = "transactions.txt";
    // To keep track of the next available ID
    private int currentId = 1;

    // Constructor: Load transactions from file when the service starts
    public TransactionService() {
        loadTransactionsFromFile();
    }

    // Read transactions from the file and load them into the list
    private void loadTransactionsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) { // 6 fields including price
                    int id = Integer.parseInt(parts[0]);
                    String itemName = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    LocalDate date = LocalDate.parse(parts[4]);
                    String type = parts[5];
                    transactions.add(new Transaction(id, itemName, quantity, price, date, type));
                    currentId = Math.max(currentId, id + 1); // Update the next available ID
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, which is fine for the first run
        }
    }

    // Save all transactions back to the file
    private void saveTransactionsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Create: Add a new transaction
    public void addTransaction(Transaction transaction) {
        transaction.setId(currentId++); // Assign a new ID and increment
        transactions.add(transaction); // Add to the list
        saveTransactionsToFile(); // Save to file
    }

    // Read: Get all transactions
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // Read: Find a transaction by ID
    public Transaction findTransactionById(int id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                return transaction;
            }
        }
        return null; // Return null if not found
    }

    // Update: Update an existing transaction
    public void updateTransaction(Transaction updatedTransaction) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId() == updatedTransaction.getId()) {
                transactions.set(i, updatedTransaction); // Replace the old transaction
                saveTransactionsToFile(); // Save changes to file
                return;
            }
        }
    }

    // Delete: Remove a transaction by ID
    public void deleteTransaction(int id) {
        transactions.removeIf(transaction -> transaction.getId() == id); // Remove matching transaction
        saveTransactionsToFile(); // Save changes to file
    }

    // Calculate total restock value (sum of prices for "Restock" transactions)
    public double getTotalRestockValue() {
        return transactions.stream()
                .filter(transaction -> "Restock".equalsIgnoreCase(transaction.getType()))
                .mapToDouble(Transaction::getPrice)
                .sum();
    }

    // Calculate total sale value (sum of prices for "Sale" transactions)
    public double getTotalSaleValue() {
        return transactions.stream()
                .filter(transaction -> "Sale".equalsIgnoreCase(transaction.getType()))
                .mapToDouble(Transaction::getPrice)
                .sum();
    }
}