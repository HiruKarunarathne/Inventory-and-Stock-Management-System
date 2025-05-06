package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final Stack<Transaction> transactions = new Stack<>();
    private final String filePath = "transactions.txt";
    private int currentId = 1;

    public TransactionService() {
        loadTransactionsFromFile();
    }

    private void loadTransactionsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Transaction> tempList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String id = parts[0];
                    String itemName = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    LocalDate date = LocalDate.parse(parts[4]);
                    String type = parts[5];
                    Transaction transaction = new Transaction(id, itemName, quantity, price, date, type);
                    logger.debug("Loaded transaction: id={}, name={}, type={}", transaction.getId(), transaction.getName(), transaction.getType());
                    tempList.add(transaction);
                    currentId = Math.max(currentId, Integer.parseInt(id) + 1);
                }
            }
            for (int i = tempList.size() - 1; i >= 0; i--) {
                transactions.push(tempList.get(i));
            }
            mergeSort(transactions, 0, transactions.size() - 1);
        } catch (IOException e) {
            logger.info("No existing transactions file found. Starting fresh.");
        }
    }

    private void saveTransactionsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Stack<Transaction> tempStack = new Stack<>();
            while (!transactions.isEmpty()) {
                tempStack.push(transactions.pop());
            }
            while (!tempStack.isEmpty()) {
                Transaction transaction = tempStack.pop();
                logger.debug("Saving transaction: id={}, name={}, type={}", transaction.getId(), transaction.getName(), transaction.getType());
                writer.write(transaction.toString());
                writer.newLine();
                transactions.push(transaction);
            }
        } catch (IOException e) {
            logger.error("Error saving transactions to file", e);
        }
    }

    public void addTransaction(Transaction transaction) {
        logger.debug("Adding transaction: id={}, name={}, type={}", transaction.getId(), transaction.getName(), transaction.getType());
        transaction.setId(String.valueOf(currentId++));
        transactions.push(transaction);
        mergeSort(transactions, 0, transactions.size() - 1);
        saveTransactionsToFile();
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactionList = new ArrayList<>(transactions);
        for (Transaction transaction : transactionList) {
            logger.debug("Listing transaction: id={}, name={}, type={}", transaction.getId(), transaction.getName(), transaction.getType());
        }
        return transactionList;
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

    private void mergeSort(Stack<Transaction> stack, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(stack, left, mid);
            mergeSort(stack, mid + 1, right);
            merge(stack, left, mid, right);
        }
    }

    private void merge(Stack<Transaction> stack, int left, int mid, int right) {
        List<Transaction> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (stack.get(i).getDate().compareTo(stack.get(j).getDate()) <= 0) {
                temp.add(stack.get(i++));
            } else {
                temp.add(stack.get(j++));
            }
        }
        while (i <= mid) temp.add(stack.get(i++));
        while (j <= right) temp.add(stack.get(j++));
        for (i = 0; i < temp.size(); i++) {
            stack.set(left + i, temp.get(i));
        }
    }
}