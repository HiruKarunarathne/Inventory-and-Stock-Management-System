package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.Transaction;
import com.intellij.InventoryAndStockManagementSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        logger.info("Test endpoint accessed");
        return "Test endpoint working! The server is running.";
    }

    @GetMapping
    public String listTransactions(Model model,
                                   @RequestParam(value = "message", required = false) String message,
                                   @RequestParam(value = "error", required = false) String error) {
        logger.info("Accessing listTransactions: message={}, error={}", message, error);
        if (transactionService == null) {
            logger.error("TransactionService is null");
        }
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        model.addAttribute("totalRestockValue", transactionService.getTotalRestockValue());
        model.addAttribute("totalSaleValue", transactionService.getTotalSaleValue());
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        return "transactionindex";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Accessing showAddForm");
        model.addAttribute("transaction", new Transaction());
        return "add-transaction";
    }

    @PostMapping
    public String addTransaction(@ModelAttribute Transaction transaction, RedirectAttributes redirectAttributes) {
        logger.info("Adding transaction: id={}, name={}, quantity={}, price={}, date={}, type={}",
                transaction.getId(), transaction.getName(), transaction.getQuantity(),
                transaction.getPrice(), transaction.getDate(), transaction.getType());
        if (transaction.getName() == null || transaction.getName().trim().isEmpty()) {
            logger.warn("Validation failed: Item name is required");
            redirectAttributes.addAttribute("error", "Item name is required!");
            return "redirect:/transactions";
        }
        if (transaction.getQuantity() <= 0) {
            logger.warn("Validation failed: Quantity must be positive");
            redirectAttributes.addAttribute("error", "Quantity must be positive!");
            return "redirect:/transactions";
        }
        if (transaction.getPrice() <= 0) {
            logger.warn("Validation failed: Price must be positive");
            redirectAttributes.addAttribute("error", "Price must be positive!");
            return "redirect:/transactions";
        }
        if (transaction.getDate() == null) {
            logger.warn("Validation failed: Date is required");
            redirectAttributes.addAttribute("error", "Date is required!");
            return "redirect:/transactions";
        }
        if (transaction.getType() == null || transaction.getType().trim().isEmpty()) {
            logger.warn("Validation failed: Type is required");
            redirectAttributes.addAttribute("error", "Type is required!");
            return "redirect:/transactions";
        }
        transactionService.addTransaction(transaction);
        logger.info("Transaction added successfully");
        redirectAttributes.addAttribute("message", "Transaction added successfully!");
        return "redirect:/transactions";
    }
}

@Controller
class RootController {
    private static final Logger logger = LoggerFactory.getLogger(RootController.class);

    @GetMapping("/")
    public String showHome() {
        logger.info("Accessing home page");
        return "index";
    }
}