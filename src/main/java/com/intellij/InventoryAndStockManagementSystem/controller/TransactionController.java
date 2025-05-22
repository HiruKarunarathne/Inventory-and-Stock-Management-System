package com.intellij.InventoryAndStockManagementSystem.controller;


import com.intellij.InventoryAndStockManagementSystem.model.Transaction;
import com.intellij.InventoryAndStockManagementSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342") // <-- CORS fix added here
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

    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<Transaction> getAllTransactions() {
        logger.info("Fetching all transactions (JSON)");
        return transactionService.getAllTransactions();
    }

    @PostMapping(consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        logger.info("Adding transaction: name={}, quantity={}, price={}, date={}, type={}",
                transaction.getName(), transaction.getQuantity(),
                transaction.getPrice(), transaction.getDate(), transaction.getType());

        if (transaction.getName() == null || transaction.getName().trim().isEmpty()) {
            logger.warn("Validation failed: Item name is required");
            return ResponseEntity.badRequest().body("Item name is required!");
        }
        if (transaction.getQuantity() <= 0) {
            logger.warn("Validation failed: Quantity must be positive");
            return ResponseEntity.badRequest().body("Quantity must be positive!");
        }
        if (transaction.getPrice() <= 0) {
            logger.warn("Validation failed: Price must be positive");
            return ResponseEntity.badRequest().body("Price must be positive!");
        }
        if (transaction.getDate() == null) {
            logger.warn("Validation failed: Date is required");
            return ResponseEntity.badRequest().body("Date is required!");
        }
        if (transaction.getType() == null || transaction.getType().trim().isEmpty()) {
            logger.warn("Validation failed: Type is required");
            return ResponseEntity.badRequest().body("Type is required!");
        }

        transactionService.addTransaction(transaction);
        logger.info("Transaction added successfully");
        return ResponseEntity.ok("Transaction added successfully!");
    }

    @GetMapping("/view")
    public String listTransactions(Model model,
                                   @RequestParam(value = "message", required = false) String message,
                                   @RequestParam(value = "error", required = false) String error) {
        logger.info("Accessing listTransactions view");
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        model.addAttribute("totalRestockValue", transactionService.getTotalRestockValue());
        model.addAttribute("totalSaleValue", transactionService.getTotalSaleValue());
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        return "transactionindex";
    }
}
