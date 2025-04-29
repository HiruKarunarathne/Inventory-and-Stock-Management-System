package com.tcs.transaction_control.service;

import com.tcs.transaction_control.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final List<Transaction> transactions = new ArrayList<>();
    private final String filePath = "transactions.txt";
    private int currentId = 1;

    public TransactionService() {
        loadTransactionsFromFile();
    }

    private void loadTransactionsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String itemName = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    LocalDate date = LocalDate.parse(parts[4]);
                    String type = parts[5];
                    transactions.add(new Transaction(id, itemName, quantity, price, date, type));
                    currentId = Math.max(currentId, id + 1);
                }
            }
        } catch (IOException e) {
            logger.info("No existing transactions file found. Starting fresh.");
        }
    }

    private void saveTransactionsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.error("Error saving transactions to file", e);
        }
    }

    public void addTransaction(Transaction transaction) {
        transaction.setId(currentId++);
        transactions.add(transaction);
        saveTransactionsToFile();
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public Transaction findTransactionById(int id) {
        return transactions.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateTransaction(Transaction updatedTransaction) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId() == updatedTransaction.getId()) {
                transactions.set(i, updatedTransaction);
                saveTransactionsToFile();
                return;
            }
        }
    }

    public void deleteTransaction(int id) {
        transactions.removeIf(transaction -> transaction.getId() == id);
        saveTransactionsToFile();
    }

    public double getTotalRestockValue() {
        return transactions.stream()
                .filter(transaction -> "Restock".equalsIgnoreCase(transaction.getType()))
                .mapToDouble(Transaction::getPrice)
                .sum();
    }

    public double getTotalSaleValue() {
        return transactions.stream()
                .filter(transaction -> "Sale".equalsIgnoreCase(transaction.getType()))
                .mapToDouble(Transaction::getPrice)
                .sum();
    }
}