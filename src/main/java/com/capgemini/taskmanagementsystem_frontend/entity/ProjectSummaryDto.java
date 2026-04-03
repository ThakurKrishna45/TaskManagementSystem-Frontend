package com.capgemini.taskmanagementsystem_frontend.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectSummaryDto {
    private String userName;
    private LocalDate dueDate;
    private String task;
    private String status;
}
