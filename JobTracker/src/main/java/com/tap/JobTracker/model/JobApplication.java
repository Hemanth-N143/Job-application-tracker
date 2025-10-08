package com.tap.JobTracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "job_applications")  // Standard naming convention
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String status;

    @Column(name = "applied_date")
    private LocalDate appliedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Matches User entity's ID
    private User user;

    // Default constructor
    public JobApplication() {
    }

    // Constructor with parameters
    public JobApplication(String company, String position, String status, User user) {
        this.company = company;
        this.position = position;
        this.status = status;
        this.user = user;
    }

    // Auto-fill appliedDate before persisting
    @PrePersist
    public void prePersist() {
        if (appliedDate == null) {
            appliedDate = LocalDate.now();
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", status='" + status + '\'' +
                ", appliedDate=" + appliedDate +
                ", user=" + user +
                '}';
    }
}
