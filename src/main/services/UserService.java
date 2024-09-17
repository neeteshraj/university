package main.services;

import main.courses.Course;
import main.data.UserData;
import main.users.Administrator;
import main.users.Professor;
import main.users.Student;
import main.users.User;

import java.util.ArrayList;
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
            List<Course> emptyAssignedCourses = new ArrayList<>();
            UserData.addUser(new Professor(name, email, password, "Computer Science", emptyAssignedCourses));
            System.out.println("Professor account created.");
        } else {
            System.out.println("Invalid role choice.");
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
            new StudentLoginService().login(scanner, (Student) user);
        } else if (user instanceof Professor) {
            new ProfessorLoginService().login(scanner, (Professor) user);
        } else if (user instanceof Administrator) {
            // Delegating the login to AdminLoginService
            new AdminLoginService().login(scanner, (Administrator) user);
        } else {
            System.out.println("No user found in the data.");
        }
    }

    public static void adminLogin(Scanner scanner) {
        System.out.println("Admin Login");
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
        new AdminLoginService().login(scanner, admin);
    }
}
