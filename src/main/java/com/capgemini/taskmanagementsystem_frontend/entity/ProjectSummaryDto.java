package com.capgemini.taskmanagementsystem_frontend.entity;

import lombok.Data;

@Data
public class ProjectSummaryDto {
    private String userName;
    private String task;
    private String name;     // project name
    private String status;
}
