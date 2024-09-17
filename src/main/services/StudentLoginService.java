package main.services;

import main.complaints.Complaint;
import main.interfaces.UserLoginService;
import main.users.Student;
import main.data.UserData;

import java.util.List;
import java.util.Scanner;

public class StudentLoginService implements UserLoginService<Student> {

    @Override
    public void login(Scanner scanner, Student student) {
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
                    System.out.println("No complaints.");
                    break;
                case 10:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}