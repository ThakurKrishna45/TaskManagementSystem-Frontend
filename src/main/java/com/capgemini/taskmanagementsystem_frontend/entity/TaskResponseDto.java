package com.capgemini.taskmanagementsystem_frontend.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponseDto {

    private String taskName;

    private String description;

    private LocalDate dueDate;

    private String priority;

    private String status;

    private String projectName;

    private String username;
}
