package com.capgemini.taskmanagementsystem_frontend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private int commentId;
    private String text;
    private LocalDateTime createdAt;
    private int userId;
    private int taskId;
    private String username;

}