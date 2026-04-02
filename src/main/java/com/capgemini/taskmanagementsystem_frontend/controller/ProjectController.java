package com.capgemini.taskmanagementsystem_frontend.controller;

import com.capgemini.taskmanagementsystem_frontend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/projects/summary")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/summary")
    public String projectSummaryForm() {
        return "pages/project-summary";
    }

    @GetMapping("/summary/find")
    public String findProjectSummary(@RequestParam Integer projectId, Model model) {
        try {
            model.addAttribute("summaries", projectService.getProjectSummary(projectId));
        } catch (Exception e) {
            model.addAttribute("error", "No summary found for project ID: " + projectId);
        }
        model.addAttribute("projectId", projectId);
        return "pages/project-summary";
    }
}
