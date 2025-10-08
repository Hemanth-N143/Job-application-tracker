package com.tap.JobTracker.controller;

import com.tap.JobTracker.model.JobApplication;
import com.tap.JobTracker.model.User;
import com.tap.JobTracker.service.JobApplicationService;
import com.tap.JobTracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jobs")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobService;

    @Autowired
    private UserService userService;

    // ✅ Show all jobs for the logged-in user
    @GetMapping
    public String getJobs(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");

        if (email != null) {
            Optional<User> user = userService.findByEmail(email);
            if (user.isPresent()) {
                List<JobApplication> jobs = jobService.getAllJobs(user.get());
                model.addAttribute("jobs", jobs);
                model.addAttribute("loggedIn", true);
                return "home"; // ✅ Show home page with jobs
            }
        }

        model.addAttribute("loggedIn", false);
        return "home"; // ✅ Show home but without jobs
    }

    // ✅ Show the add job form
    @GetMapping("/add/{email}")
    public String showAddJobForm(@PathVariable String email, Model model) {
        model.addAttribute("job", new JobApplication());
        model.addAttribute("email", email);
        return "addJob";
    }

    // ✅ Add Job and Redirect to Home Page
    @PostMapping("/add")
    public String addJob(@RequestParam String email, @ModelAttribute JobApplication job, HttpSession session) {
        Optional<User> user = userService.findByEmail(email);

        if (user.isPresent()) {
            job.setUser(user.get());
            jobService.addJob(job);
            session.setAttribute("userEmail", email);
        }

        return "redirect:/home"; // ✅ Always go back to home
    }

    // ✅ Show the edit job form
    @GetMapping("/edit/{id}")
    public String showEditJobForm(@PathVariable Long id, Model model) {
        Optional<JobApplication> job = jobService.getJobById(id);
        if (job.isPresent()) {
            model.addAttribute("job", job.get());
            return "editJob";
        }
        model.addAttribute("error", "Job not found");
        return "error";
    }

    // ✅ Update an existing job
    @PostMapping("/update/{id}")
    public String updateJob(@PathVariable Long id, @ModelAttribute JobApplication job, HttpSession session, Model model) {
        Optional<JobApplication> updatedJob = jobService.updateJob(id, job);
        if (updatedJob.isPresent()) {
            return "redirect:/home"; // ✅ Redirect to home page
        }
        model.addAttribute("error", "Job not found");
        return "error";
    }

    // ✅ Delete a job (GET Mapping)
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id, HttpSession session, Model model) {
        Optional<JobApplication> job = jobService.getJobById(id);
        if (job.isPresent()) {
            jobService.deleteJob(id);
            return "redirect:/home"; // ✅ Redirect to home page
        }
        model.addAttribute("error", "Job not found");
        return "error";
    }

    // ✅ Delete a job (RESTful DELETE API)
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteJobApi(@PathVariable Long id) {
        Optional<JobApplication> job = jobService.getJobById(id);
        if (job.isPresent()) {
            jobService.deleteJob(id);
            return ResponseEntity.ok("Job deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
    }
}
