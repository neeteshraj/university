package main.complaints;

import java.time.LocalDateTime;

public class Complaint {
    private static int counter = 1;
    private final int id;
    private final String studentEmail;
    private final String description;
    private String status;
    private final LocalDateTime submissionDate;
    private String resolutionDetails;

    public Complaint(String studentEmail, String description) {
        this.id = counter++;
        this.studentEmail = studentEmail;
        this.description = description;
        this.status = "Pending";
        this.submissionDate = LocalDateTime.now();
        this.resolutionDetails = "";
    }

    public int getId() {
        return id;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public String getResolutionDetails() {
        return resolutionDetails;
    }

    public void setResolutionDetails(String resolutionDetails) {
        this.resolutionDetails = resolutionDetails;
    }

    @Override
    public String toString() {
        return "Complaint ID: " + id + "\n" +
                "Student Email: " + studentEmail + "\n" +
                "Description: " + description + "\n" +
                "Status: " + status + "\n" +
                "Submission Date: " + submissionDate + "\n" +
                "Resolution Details: " + resolutionDetails + "\n";
    }
}
