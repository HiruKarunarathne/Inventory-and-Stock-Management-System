package com.tcs.transaction_control.controller;

import com.tcs.transaction_control.model.Transaction;
import com.tcs.transaction_control.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Test endpoint working! The server is running.";
    }

    @GetMapping
    public String listTransactions(Model model,
                                   @RequestParam(value = "message", required = false) String message,
                                   @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        model.addAttribute("totalRestockValue", transactionService.getTotalRestockValue());
        model.addAttribute("totalSaleValue", transactionService.getTotalSaleValue());
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        return "transactionindex";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "add-transaction";
    }

    @PostMapping
    public String addTransaction(@ModelAttribute Transaction transaction, RedirectAttributes redirectAttributes) {
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
        transactionService.addTransaction(transaction);
        redirectAttributes.addAttribute("message", "Transaction added successfully!");
        return "redirect:/transactions";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Transaction transaction = transactionService.findTransactionById(id);
        if (transaction == null) {
            redirectAttributes.addAttribute("error", "Transaction not found!");
            return "redirect:/transactions";
        }
        model.addAttribute("transaction", transaction);
        return "edit-transaction";
    }

    @PostMapping("/update")
    public String updateTransaction(@ModelAttribute Transaction transaction, RedirectAttributes redirectAttributes) {
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
        transactionService.updateTransaction(transaction);
        redirectAttributes.addAttribute("message", "Transaction updated successfully!");
        return "redirect:/transactions";
    }

    @GetMapping("/delete")
    public String deleteTransaction(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        transactionService.deleteTransaction(id);
        redirectAttributes.addAttribute("message", "Transaction deleted successfully!");
        return "redirect:/transactions";
    }
}

@Controller
class RootController {
    @GetMapping("/")
    public String showHome() {
        return "index";
    }
}