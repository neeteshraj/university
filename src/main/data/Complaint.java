package main.data;

public class Complaint {
    private static int idCounter = 1;
    private final int id;
    private final String studentEmail;
    private final String description;
    private String status;

    public Complaint(String studentEmail, String description) {
        this.id = idCounter++;
        this.studentEmail = studentEmail;
        this.description = description;
        this.status = "Pending";
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

    @Override
    public String toString() {
        return "Complaint ID: " + id + "\n" +
                "Student Email: " + studentEmail + "\n" +
                "Description: " + description + "\n" +
                "Status: " + status;
    }
}
