package com.capgemini.taskmanagementsystem_frontend.controller;

import com.capgemini.taskmanagementsystem_frontend.entity.LoginRequest;
import com.capgemini.taskmanagementsystem_frontend.service.LoginFrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private LoginFrontendService loginService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login-process")
    public String processLogin(@ModelAttribute("loginRequest") LoginRequest loginRequest, Model model) {
        boolean success = loginService.callBackendLogin(loginRequest);

        if (success) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
}
