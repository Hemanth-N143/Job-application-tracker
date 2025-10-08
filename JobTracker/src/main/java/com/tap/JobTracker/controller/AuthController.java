package com.tap.JobTracker.controller;

import com.tap.JobTracker.model.User;
import com.tap.JobTracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Show Login Page
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid credentials! Try again.");
        }
        return "login"; // Returns login.jsp
    }

    // Process Login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        Optional<User> foundUser = userService.findByEmail(email);
        if (foundUser.isPresent() && foundUser.get().getPassword().equals(password)) {
            session.setAttribute("loggedInUser", foundUser.get()); // Store user in session
            return "redirect:/home"; // Redirect to dashboard.jsp
        }
        model.addAttribute("error", "Invalid credentials!");
        return "login";
    }

    // Show Register Page
    @GetMapping("/register")
    public String showRegisterPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Registration failed. Try again.");
        }
        return "register"; // Returns register.jsp
    }

    // Process Registration
    @PostMapping("/register")
    public String registerUser(@RequestParam String userName, @RequestParam String email, @RequestParam String password, Model model) {
        if (userName == null || userName.trim().isEmpty()) {
            model.addAttribute("error", "Full name is required.");
            return "register";
        }

        User newUser = new User(0L, userName, email, password);
        boolean isRegistered = userService.registerUser(newUser);

        if (!isRegistered) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        return "redirect:/auth/login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
