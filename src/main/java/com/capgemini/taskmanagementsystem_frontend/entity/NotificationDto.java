package com.capgemini.taskmanagementsystem_frontend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto {
    private Integer notificationID;
    private String text;
    private LocalDateTime createdAt;
}
