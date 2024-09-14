package main.services;

import main.data.UserData;
import main.users.Professor;
import main.users.Student;
import main.users.User;

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
            Student student = (Student) user;
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
                        student.calculateSGPA();
                        student.calculateCGPA();
                        break;
                    case 8:
                        System.out.println("Logged out.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Login successful! Welcome, " + user.getName() + " (Professor)");
            // Implement professor functionalities if needed
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the University System");
        while (true) {
            System.out.println("1. Enter the Application");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Application Menu");
                System.out.println("1. Create Account (Student/Professor)");
                System.out.println("2. Login");
                System.out.println("3. Exit");

                int appChoice = scanner.nextInt();
                scanner.nextLine();

                switch (appChoice) {
                    case 1:
                        createUser(scanner);
                        break;
                    case 2:
                        loginUser(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else if (choice == 2) {
                System.out.println("Exiting...");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
