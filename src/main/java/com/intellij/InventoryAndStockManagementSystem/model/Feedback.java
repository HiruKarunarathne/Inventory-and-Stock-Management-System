package com.intellij.InventoryAndStockManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class Feedback {
    private String id;
    private String type;
    private String message;
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date date;
    private String status;


    public Feedback() {
    }

    public Feedback(String id, String type, String message, String userId, Date date, String status) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.userId = userId;
        this.date = date;
        this.status = status;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
