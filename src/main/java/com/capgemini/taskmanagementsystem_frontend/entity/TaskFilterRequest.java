package com.capgemini.taskmanagementsystem_frontend.entity;

import lombok.Data;

@Data
public class TaskFilterRequest {

    private String priority;
    private String status;
}
