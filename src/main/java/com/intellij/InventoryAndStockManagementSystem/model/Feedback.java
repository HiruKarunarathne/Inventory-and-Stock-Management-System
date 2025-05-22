package com.intellij.InventoryAndStockManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Feedback {

    private String id;
    private String type;
    private String message;
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date date;
    private String status;

}
