package com.tap.JobTracker.controller;

import com.tap.JobTracker.model.JobApplication;
import com.tap.JobTracker.model.User;
import com.tap.JobTracker.service.JobApplicationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ViewController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @GetMapping("/home")
    public String showHomePage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            List<JobApplication> jobs = jobApplicationService.getJobsByUserEmail(loggedInUser.getEmail());
            model.addAttribute("jobs", jobs);
        }

        return "home";
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/dashboard")
    public String showDashboardPage() {
        return "main";
    }

    @GetMapping("/addJob")
    public String showAddJobPage() {
        return "addJob";
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }
}
