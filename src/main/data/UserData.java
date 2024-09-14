package main.data;

import main.users.Administrator;
import main.users.Student;
import main.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserData {
    private static final List<User> users = new ArrayList<>();
    private static final List<Complaint> complaints = new ArrayList<>();

    static {
        users.add(new Administrator("Admin", "admin@university.com", "admin123"));
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static User loginUser(String email, String password) {
        for (User user : users) {
            if (user.login(email, password)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isEmailTaken(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static void submitComplaint(String studentEmail, String description) {
        complaints.add(new Complaint(studentEmail, description));
    }

    public static List<Complaint> getComplaintsByStudent(String email) {
        return complaints.stream()
                .filter(c -> c.getStudentEmail().equals(email))
                .collect(Collectors.toList());
    }

    public static List<Complaint> getAllComplaints() {
        return new ArrayList<>(complaints);
    }

    public static void resolveComplaint(int id) {
        for (Complaint complaint : complaints) {
            if (complaint.getId() == id) {
                complaint.setStatus("Resolved");
                return;
            }
        }
    }

    public static List<Student> getStudents() {
        return users.stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .collect(Collectors.toList());
    }

    public static Student getStudentByEmail(String email) {
        return users.stream()
                .filter(user -> user instanceof Student && user.getEmail().equals(email))
                .map(user -> (Student) user)
                .findFirst()
                .orElse(null);
    }

}
