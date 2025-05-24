package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.MaintenanceRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// Service for handling maintenance request-related operations
@Service
public class MaintenanceRequestService {
    private final Stack<MaintenanceRequest> stack = new Stack<>();
    private final String filename = "maintenance.txt";

    // Initializes the service by loading data from file
    public void init() {
        loadFromFile();
    }

    // Loads maintenance requests from the file
    public synchronized void loadFromFile() {
        stack.clear();
        Path path = Paths.get(filename);
        if (!Files.exists(path)) return;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            List<MaintenanceRequest> tempList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                MaintenanceRequest req = MaintenanceRequest.fromFileString(line);
                if (req != null) tempList.add(req);
            }
            Collections.reverse(tempList);
            for (MaintenanceRequest req : tempList) {
                stack.push(req);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Saves maintenance requests to the file
    public synchronized void saveToFile() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filename))) {
            List<MaintenanceRequest> tempList = new ArrayList<>(stack);
            Collections.reverse(tempList);
            for (MaintenanceRequest req : tempList) {
                bw.write(req.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Adds a new maintenance request
    public synchronized void addRequest(MaintenanceRequest req) {
        stack.push(req);
        saveToFile();
    }

    // Retrieves all maintenance requests
    public synchronized List<MaintenanceRequest> getAllRequests() {
        return new ArrayList<>(stack);
    }

    // Retrieves all maintenance requests, sorted by date
    public synchronized List<MaintenanceRequest> getAllRequestsSortedByDate() {
        List<MaintenanceRequest> list = new ArrayList<>(stack);
        mergeSort(list, 0, list.size() - 1);
        return list;
    }

    // Sorts the list of maintenance requests using merge sort algorithm
    private void mergeSort(List<MaintenanceRequest> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    // Merges two sorted sub-lists
    private void merge(List<MaintenanceRequest> list, int left, int mid, int right) {
        List<MaintenanceRequest> leftList = new ArrayList<>(list.subList(left, mid + 1));
        List<MaintenanceRequest> rightList = new ArrayList<>(list.subList(mid + 1, right + 1));
        int i = 0, j = 0, k = left;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).getRequestDate().isBefore(rightList.get(j).getRequestDate())) {
                list.set(k++, leftList.get(i++));
            } else {
                list.set(k++, rightList.get(j++));
            }
        }
        while (i < leftList.size()) list.set(k++, leftList.get(i++));
        while (j < rightList.size()) list.set(k++, rightList.get(j++));
    }

    // Updates an existing maintenance request
    public synchronized boolean updateRequest(int requestId, MaintenanceRequest updated) {
        Stack<MaintenanceRequest> temp = new Stack<>();
        boolean found = false;
        while (!stack.isEmpty()) {
            MaintenanceRequest req = stack.pop();
            if (req.getRequestId() == requestId) {
                updated.setRequestId(requestId);
                temp.push(updated);
                found = true;
                break;
            } else {
                temp.push(req);
            }
        }
        while (!stack.isEmpty()) temp.push(stack.pop());
        while (!temp.isEmpty()) stack.push(temp.pop());
        if (found) saveToFile();
        return found;
    }

    // Deletes a maintenance request by its ID
    public synchronized boolean deleteRequest(int requestId) {
        Stack<MaintenanceRequest> temp = new Stack<>();
        boolean found = false;
        while (!stack.isEmpty()) {
            MaintenanceRequest req = stack.pop();
            if (req.getRequestId() == requestId) {
                found = true;
                break;
            } else {
                temp.push(req);
            }
        }
        while (!stack.isEmpty()) temp.push(stack.pop());
        while (!temp.isEmpty()) stack.push(temp.pop());
        if (found) saveToFile();
        return found;
    }

    // Gets the next available request ID
    public synchronized int getNextRequestId() {
        int maxId = 0;
        for (MaintenanceRequest req : stack) {
            if (req.getRequestId() > maxId) maxId = req.getRequestId();
        }
        return maxId + 1;
    }

}
