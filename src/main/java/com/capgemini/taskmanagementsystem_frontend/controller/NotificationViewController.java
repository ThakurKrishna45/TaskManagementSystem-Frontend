package com.capgemini.taskmanagementsystem_frontend.controller;

import com.capgemini.taskmanagementsystem_frontend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notifications")
public class NotificationViewController {

    @Autowired
    private NotificationService apiService;

    @GetMapping
    public String notificationsForm() {
        return "pages/notifications";
    }

    @GetMapping("/find-one")
    public String findById(@RequestParam Integer id, Model model) {
        try {
            model.addAttribute("notification", apiService.getNotificationById(id));
        } catch (Exception e) {
            model.addAttribute("error", "Notification not found for ID: " + id);
        }
        model.addAttribute("searchId", id);
        return "pages/notifications";
    }

    @GetMapping("/find-recent")
    public String findRecent(@RequestParam Integer userId, @RequestParam Integer n, Model model) {
        try {
            model.addAttribute("notifications", apiService.getNRecentNotifications(userId, n));
        } catch (Exception e) {
            model.addAttribute("error", "Could not fetch notifications for user ID: " + userId);
        }
        model.addAttribute("userId", userId);
        model.addAttribute("n", n);
        return "pages/notifications";
    }
}
