package com.intellij.InventoryAndStockManagementSystem.service;

import com.intellij.InventoryAndStockManagementSystem.model.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// Service for handling feedback-related operations
@Service
public class FeedbackService {
    private final FileStorageService fileStorageService;
    private static final String FILE_NAME = "feedback.txt";

    // Constructor for FeedbackService, injecting FileStorageService
    public FeedbackService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    // Retrieves all feedback from file
    public List<Feedback> getAllFeedback() {
        return fileStorageService.readFromFile(FILE_NAME, Feedback.class);
    }

    // Creates new feedback and saves it to file
    public Feedback createFeedback(Feedback feedback) {
        List<Feedback> feedbackList = getAllFeedback();
        feedback.setId(UUID.randomUUID().toString());
        feedbackList.add(feedback);
        fileStorageService.writeToFile(FILE_NAME, feedbackList);
        return feedback;
    }

    // Updates existing feedback by ID and saves it to file
    public Feedback updateFeedback(String id, Feedback feedback) {
        List<Feedback> feedbackList = getAllFeedback();
        Feedback existingFeedback = feedbackList.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        // Preserve original date if not provided in update
        if (feedback.getDate() == null) {
            feedback.setDate(existingFeedback.getDate());
        }

        feedback.setId(id);
        feedbackList.removeIf(f -> f.getId().equals(id));
        feedbackList.add(feedback);
        fileStorageService.writeToFile(FILE_NAME, feedbackList);
        return feedback;
    }

    // Deletes feedback by ID from file
    public void deleteFeedback(String id) {
        List<Feedback> feedback = getAllFeedback();
        feedback.removeIf(i -> i.getId().equals(id));
        fileStorageService.writeToFile(FILE_NAME, feedback);
    }
}
