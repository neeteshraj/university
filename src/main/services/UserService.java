package main.services;

import main.data.Complaint;
import main.data.UserData;
import main.users.Administrator;
import main.users.Professor;
import main.users.Student;
import main.users.User;

import java.util.List;
import java.util.Scanner;

public class UserService {

    public static void createUser(Scanner scanner) {
        System.out.println("Create Account");
        System.out.println("1. Student");
        System.out.println("2. Professor");

        int roleChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        if (UserData.isEmailTaken(email)) {
            System.out.println("Email already in use. Try another email.");
            return;
        }

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (roleChoice == 1) {
            System.out.println("Enter branch name:");
            String branchName = scanner.nextLine();
            UserData.addUser(new Student(name, email, password, branchName, 1));
            System.out.println("Student account created.");
        } else if (roleChoice == 2) {
            UserData.addUser(new Professor(name, email, password));
            System.out.println("Professor account created.");
        } else {
            System.out.println("Invalid role choice.");
        }
    }

    public static void loginStudent(Scanner scanner, Student student) {
        System.out.println("Login successful! Welcome, " + student.getName() + " (Student)");

        while (true) {
            student.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    student.viewAvailableCourses();
                    break;
                case 2:
                    System.out.println("Enter course code to register:");
                    String courseCode = scanner.nextLine();
                    student.registerForCourse(courseCode);
                    break;
                case 3:
                    System.out.println("Enter course code to drop:");
                    String dropCourseCode = scanner.nextLine();
                    student.dropCourse(dropCourseCode);
                    break;
                case 4:
                    student.viewWeeklySchedule();
                    break;
                case 5:
                    student.completeSemester();
                    break;
                case 6:
                    student.viewGrades();
                    break;
                case 7:
                    System.out.println("SGPA: " + student.calculateSGPA());
                    System.out.println("CGPA: " + student.calculateCGPA());
                    break;
                case 8:
                    System.out.println("Enter your complaint description:");
                    String description = scanner.nextLine();
                    UserData.submitComplaint(student.getEmail(), description);
                    System.out.println("Complaint submitted.");
                    break;
                case 9:
                    List<Complaint> complaints = UserData.getComplaintsByStudent(student.getEmail());
                    if (complaints.isEmpty()) {
                        System.out.println("You have no complaints.");
                    } else {
                        complaints.forEach(System.out::println);
                    }
                    break;
                case 10:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void loginProfessor(Scanner scanner, Professor professor) {
        System.out.println("Login successful! Welcome, " + professor.getName() + " (Professor)");

    }

    public static void adminLogin(Scanner scanner) {
        System.out.println("Login");
        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User user = UserData.loginUser(email, password);

        if (user == null || !(user instanceof Administrator)) {
            System.out.println("Invalid email or password or not an admin.");
            return;
        }

        Administrator admin = (Administrator) user;
        System.out.println("Login successful! Welcome, " + admin.getName() + " (Administrator)");

        while (true) {
            admin.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    AdminService.manageCourses(scanner);
                    break;
                case 2:
                    AdminService.manageStudents(scanner);
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void loginUser(Scanner scanner) {
        System.out.println("Login");
        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User user = UserData.loginUser(email, password);

        if (user == null) {
            System.out.println("Invalid email or password.");
            return;
        }

        if (user instanceof Student) {
            loginStudent(scanner, (Student) user);
        } else if (user instanceof Professor) {
            loginProfessor(scanner, (Professor) user);
        } else {
            System.out.println("No user found in the data.");
        }
    }
}
