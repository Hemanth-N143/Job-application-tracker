package com.tap.JobTracker.repository;

import com.tap.JobTracker.model.JobApplication;
import com.tap.JobTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUser(User user);

    List<JobApplication> findByUserEmail(String email);
}
