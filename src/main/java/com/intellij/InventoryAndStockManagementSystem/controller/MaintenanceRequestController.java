package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.MaintenanceRequest;
import com.intellij.InventoryAndStockManagementSystem.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// Controller for handling maintenance request-related operations
@RestController
@RequestMapping("/maintenance")
@CrossOrigin(origins = "*")
public class MaintenanceRequestController {
    @Autowired
    private MaintenanceRequestService service;

    // Adds a new maintenance request
    @PostMapping("/add")
    public ResponseEntity<String> addRequest(@RequestBody MaintenanceRequest req) {
        if (req.getRequestDate() == null) req.setRequestDate(LocalDate.now());
        req.setRequestId(service.getNextRequestId());
        service.addRequest(req);
        return ResponseEntity.ok("Maintenance request added successfully with ID: " + req.getRequestId());
    }

    // Retrieves all maintenance requests, optionally sorted by date
    @GetMapping("/all")
    public List<MaintenanceRequest> getAllRequests(@RequestParam(value = "sorted", required = false) Boolean sorted) {
        if (sorted != null && sorted) {
            return service.getAllRequestsSortedByDate();
        } else {
            return service.getAllRequests();
        }
    }

    // Updates an existing maintenance request
    @PutMapping("/update/{requestId}")
    public ResponseEntity<String> updateRequest(@PathVariable int requestId, @RequestBody MaintenanceRequest req) {
        boolean updated = service.updateRequest(requestId, req);
        if (updated) {
            return ResponseEntity.ok("Maintenance request updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request ID not found.");
        }
    }

    // Deletes a maintenance request by its ID
    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity<String> deleteRequest(@PathVariable int requestId) {
        boolean deleted = service.deleteRequest(requestId);
        if (deleted) {
            return ResponseEntity.ok("Maintenance request deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request ID not found.");
        }
    }
}
