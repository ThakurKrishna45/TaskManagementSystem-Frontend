package com.capgemini.taskmanagementsystem_frontend.controller;

import com.capgemini.taskmanagementsystem_frontend.entity.ProjectSummaryWrapperDto;
import com.capgemini.taskmanagementsystem_frontend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/summary")
    public String projectSummaryForm() {
        return "ProjectSummary";
    }

    @GetMapping("/summary/find")
    public String findProjectSummary(@RequestParam Integer projectId, Model model) {

        try {
            ProjectSummaryWrapperDto response = projectService.getProjectSummary(projectId);

            model.addAttribute("projectName", response.getProjectName());
            model.addAttribute("startDate", response.getStartDate());
            model.addAttribute("endDate", response.getEndDate());
            model.addAttribute("summaries", response.getSummaries());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", "No summary found for project ID: " + projectId);
        }

        model.addAttribute("projectId", projectId);
        return "ProjectSummary";
    }
}
