package main.data;

import main.complaints.Complaint;
import main.courses.Course;
import main.users.Administrator;
import main.users.Professor;
import main.users.Student;
import main.users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserData {
    private static final List<User> users = new ArrayList<>();
    private static final List<Complaint> complaints = new ArrayList<>();

    static {
        users.add(new Administrator("Admin", "admin@university.com", "admin123"));

        //students
        users.add(new Student("John Doe", "john.doe@university.com", "password123", "Computer Science", 1));
        users.add(new Student("Jane Smith", "jane.smith@university.com", "password456", "Mathematics", 2));
        users.add(new Student("Mike Johnson", "mike.johnson@university.com", "password789", "Physics", 3));

        // Professors
        Professor professor1 = new Professor("Dr. Alice Brown", "alice.brown@university.com", "prof123", "Computer Science");
        Professor professor2 = new Professor("Dr. Bob White", "bob.white@university.com", "prof456", "Mathematics");
        Professor professor3 = new Professor("Dr. Carol Green", "carol.green@university.com", "prof789", "Physics");

        // Assign courses to professors
        Course csCourse = CourseData.getCourseByCode("CS101");
        Course mathCourse = CourseData.getCourseByCode("MATH201");
        Course physicsCourse = CourseData.getCourseByCode("PHY301");

        professor1.assignCourse(csCourse);
        professor2.assignCourse(mathCourse);
        professor3.assignCourse(physicsCourse);

        users.add(professor1);
        users.add(professor2);
        users.add(professor3);
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

    public static List<Complaint> filterComplaintsByStatus(String status) {
        return complaints.stream()
                .filter(c -> c.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public static List<Complaint> filterComplaintsByDate(LocalDate date) {
        return complaints.stream()
                .filter(c -> c.getSubmissionDate().toLocalDate().isEqual(date))
                .collect(Collectors.toList());
    }
}
