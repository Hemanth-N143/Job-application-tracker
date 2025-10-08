package com.tap.JobTracker.service;

import com.tap.JobTracker.model.JobApplication;
import com.tap.JobTracker.model.User;
import com.tap.JobTracker.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobRepository;

    // ✅ Get all job applications for a user (by email)
    public List<JobApplication> getJobsByUserEmail(String email) {
        return jobRepository.findByUserEmail(email); // 🔥 FIXED fetching logic
    }

    // ✅ Add a new job application
    public JobApplication addJob(JobApplication job) {
        return jobRepository.save(job);
    }

    // ✅ Update a job application
    public Optional<JobApplication> updateJob(Long id, JobApplication updatedJob) {
        return jobRepository.findById(id).map(job -> {
            job.setCompany(updatedJob.getCompany());
            job.setPosition(updatedJob.getPosition());
            job.setStatus(updatedJob.getStatus());
            job.setAppliedDate(updatedJob.getAppliedDate());
            return jobRepository.save(job);
        });
    }

    // ✅ Delete a job application (Fixed Return Value)
    public boolean deleteJob(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;  // 🔥 FIXED to return true if deletion is successful
        }
        return false;
    }

    // ✅ Get a job by ID
    public Optional<JobApplication> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public List<JobApplication> getAllJobs(User user) {
        return jobRepository.findByUser(user);
    }
}
