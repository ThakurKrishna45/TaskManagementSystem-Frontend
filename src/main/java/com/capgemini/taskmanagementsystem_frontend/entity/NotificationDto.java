package com.capgemini.taskmanagementsystem_frontend.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class NotificationDto {
    private Integer notificationID;
    private String text;
    private LocalDateTime createdAt;
    private String fullName;

    public String getFormattedDate() {
        if (createdAt == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a, dd MMM yyyy");
        return createdAt.format(formatter);
    }
}
