package com.capgemini.taskmanagementsystem_frontend.controller;

import com.capgemini.taskmanagementsystem_frontend.entity.TaskFilterRequest;
import com.capgemini.taskmanagementsystem_frontend.entity.TaskResponseDto;
import com.capgemini.taskmanagementsystem_frontend.service.TaskFrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskFrontendController {

    @Autowired
    private TaskFrontendService taskService;

    @GetMapping("/tasks/by-filter")
    public String loadPage(Model model) {
        model.addAttribute("taskFilterRequest", new TaskFilterRequest());
        return "TaskByFilters";
    }

    @GetMapping("/tasks/by-filter/find")
    public String getTasks(
            @ModelAttribute("taskFilterRequest") TaskFilterRequest request,
            Model model) {

        try {
            List<TaskResponseDto> tasks =
                    taskService.getTasks(request);

            model.addAttribute("tasks", tasks);
            model.addAttribute("priority", request.getPriority());
            model.addAttribute("status", request.getStatus());

        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch tasks");
        }

        return "TaskByFilters";
    }
}