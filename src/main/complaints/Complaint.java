package main.complaints;

public class Complaint {
    private String description;
    private String status; // "Pending" or "Resolved"

    public Complaint(String description) {
        this.description = description;
        this.status = "Pending";
    }

    public void resolveComplaint() {
        this.status = "Resolved";
    }

    public String getStatus() {
        return this.status;
    }
}
