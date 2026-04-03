package com.capgemini.taskmanagementsystem_frontend.controller;

import com.capgemini.taskmanagementsystem_frontend.entity.CommentDto;
import com.capgemini.taskmanagementsystem_frontend.service.CommentFrontendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentFrontendController {

    @Autowired
    private CommentFrontendService commentService;

    // ✅ Load Page
    @GetMapping("/comments")
    public String loadPage() {
        return "CommentPage";
    }

    // ✅ Fetch Comments by Task ID
    @GetMapping("/comments/find")
    public String getComments(
            @RequestParam("taskId") Integer taskId,
            Model model) {

        try {
            List<CommentDto> comments =
                    commentService.getCommentsByTaskId(taskId);

            model.addAttribute("comments", comments);
            model.addAttribute("taskId", taskId);

        } catch (Exception e) {

            String errorMessage = e.getMessage();

            if (errorMessage == null || errorMessage.isEmpty()) {
                errorMessage = "Failed to fetch comments. Please try again.";
            }

            model.addAttribute("error", errorMessage);
        }

        return "CommentPage";
    }
}