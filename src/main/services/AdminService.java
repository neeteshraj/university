package main.services;

import main.data.CourseData;
import main.courses.Course;

import java.util.List;
import java.util.Scanner;

public class AdminService {

    public static void viewCourses() {
        List<Course> courses = CourseData.getCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course course : courses) {
                System.out.println("======================================");
                System.out.println("Course Code: " + course.getCourseCode());
                System.out.println("Title: " + course.getTitle());
                System.out.println("Professor: " + course.getProfessor());
                System.out.println("Timings: " + course.getTimings());
                System.out.println("Location: " + course.getLocation());
                System.out.println("Semester: "+course.getSemester());
                System.out.println("======================================");
            }
        }
    }

    public static void addCourse(Scanner scanner) {
        System.out.println("Add Course");
        System.out.println("Enter course code:");
        String courseCode = scanner.nextLine();

        System.out.println("Enter course title:");
        String title = scanner.nextLine();

        System.out.println("Enter professor:");
        String professor = scanner.nextLine();

        System.out.println("Enter credits:");
        int credits = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter prerequisites:");
        String prerequisites = scanner.nextLine();

        System.out.println("Enter timings:");
        String timings = scanner.nextLine();

        System.out.println("Enter semester:");
        int semester = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter location:");
        String location = scanner.nextLine();

        Course course = new Course(courseCode, title, professor, credits, prerequisites, timings, semester, location);
        CourseData.addCourse(course);
        System.out.println("Course added successfully.");
    }

    public static void deleteCourse(Scanner scanner) {
        System.out.println("Delete Course");
        System.out.println("Enter course code to delete:");
        String courseCode = scanner.nextLine();

        if (CourseData.deleteCourse(courseCode)) {
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public static void manageCourses(Scanner scanner) {
        while (true) {
            System.out.println("Course Management Menu");
            System.out.println("1. View Courses");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Go Back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewCourses();
                    break;
                case 2:
                    addCourse(scanner);
                    break;
                case 3:
                    deleteCourse(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
