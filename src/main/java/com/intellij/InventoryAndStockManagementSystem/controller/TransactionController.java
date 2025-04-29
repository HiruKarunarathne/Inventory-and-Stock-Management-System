package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.Transaction;

import com.intellij.InventoryAndStockManagementSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService; // Inject the service

    // Display all transactions
    @GetMapping
    public String listTransactions(Model model,
                                   @RequestParam(value = "message", required = false) String message,
                                   @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("transactions", transactionService.getAllTransactions()); // Add transactions to the model
        model.addAttribute("totalRestockValue", transactionService.getTotalRestockValue()); // Add total restock value
        model.addAttribute("totalSaleValue", transactionService.getTotalSaleValue()); // Add total sale value
        model.addAttribute("message", message); // Success message (if any)
        model.addAttribute("error", error); // Error message (if any)
        return "transactionindex"; // Return the transactionindex.html template
    }

    // Show form to add a new transaction
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("transaction", new Transaction()); // Create a new Transaction object for the form
        return "add-transaction"; // Return the add-transaction.html template
    }

    // Handle form submission to add a new transaction
    @PostMapping
    public String addTransaction(@ModelAttribute Transaction transaction, RedirectAttributes redirectAttributes) {
        // Validate the transaction
        if (transaction.getItemName() == null || transaction.getItemName().trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "Item name is required!");
            return "redirect:/transactions";
        }
        if (transaction.getQuantity() <= 0) {
            redirectAttributes.addAttribute("error", "Quantity must be positive!");
            return "redirect:/transactions";
        }
        if (transaction.getPrice() <= 0) {
            redirectAttributes.addAttribute("error", "Price must be positive!");
            return "redirect:/transactions";
        }
        if (transaction.getDate() == null) {
            redirectAttributes.addAttribute("error", "Date is required!");
            return "redirect:/transactions";
        }
        if (transaction.getType() == null || transaction.getType().trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "Type is required!");
            return "redirect:/transactions";
        }
        transactionService.addTransaction(transaction); // Add the transaction
        redirectAttributes.addAttribute("message", "Transaction added successfully!");
        return "redirect:/transactions"; // Redirect to the list page
    }

    // Show form to edit an existing transaction
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Transaction transaction = transactionService.findTransactionById(id); // Find the transaction by ID
        if (transaction == null) {
            redirectAttributes.addAttribute("error", "Transaction not found!");
            return "redirect:/transactions";
        }
        model.addAttribute("transaction", transaction); // Add the transaction to the model
        return "edit-transaction"; // Return the edit-transaction.html template
    }

    // Handle form submission to update a transaction
    @PostMapping("/update")
    public String updateTransaction(@ModelAttribute Transaction transaction, RedirectAttributes redirectAttributes) {
        // Validate the transaction
        if (transaction.getItemName() == null || transaction.getItemName().trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "Item name is required!");
            return "redirect:/transactions";
        }
        if (transaction.getQuantity() <= 0) {
            redirectAttributes.addAttribute("error", "Quantity must be positive!");
            return "redirect:/transactions";
        }
        if (transaction.getPrice() <= 0) {
            redirectAttributes.addAttribute("error", "Price must be positive!");
            return "redirect:/transactions";
        }
        if (transaction.getDate() == null) {
            redirectAttributes.addAttribute("error", "Date is required!");
            return "redirect:/transactions";
        }
        if (transaction.getType() == null || transaction.getType().trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "Type is required!");
            return "redirect:/transactions";
        }
        transactionService.updateTransaction(transaction); // Update the transaction
        redirectAttributes.addAttribute("message", "Transaction updated successfully!");
        return "redirect:/transactions"; // Redirect to the list page
    }

    // Delete a transaction
    @GetMapping("/delete")
    public String deleteTransaction(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        transactionService.deleteTransaction(id); // Delete the transaction
        redirectAttributes.addAttribute("message", "Transaction deleted successfully!");
        return "redirect:/transactions"; // Redirect to the list page
    }
}
