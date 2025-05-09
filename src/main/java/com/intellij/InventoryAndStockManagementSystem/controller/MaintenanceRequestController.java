package com.intellij.InventoryAndStockManagementSystem.controller;

import com.intellij.InventoryAndStockManagementSystem.model.MaintenanceRequest;
import com.intellij.InventoryAndStockManagementSystem.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceRequestController {
    @Autowired
    private MaintenanceRequestService service;

    @PostMapping("/add")
    public ResponseEntity<String> addRequest(@RequestBody MaintenanceRequest req) {
        if (req.getRequestDate() == null) req.setRequestDate(LocalDate.now());
        req.setRequestId(service.getNextRequestId());
        service.addRequest(req);
        return ResponseEntity.ok("Maintenance request added successfully with ID: " + req.getRequestId());
    }

    @GetMapping("/all")
    public List<MaintenanceRequest> getAllRequests(@RequestParam(value = "sorted", required = false) Boolean sorted) {
        if (sorted != null && sorted) {
            return service.getAllRequestsSortedByDate();
        } else {
            return service.getAllRequests();
        }
    }

    @PutMapping("/update/{requestId}")
    public ResponseEntity<String> updateRequest(@PathVariable int requestId, @RequestBody MaintenanceRequest req) {
        boolean updated = service.updateRequest(requestId, req);
        if (updated) {
            return ResponseEntity.ok("Maintenance request updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request ID not found.");
        }
    }

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
