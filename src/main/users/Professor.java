package main.users;

import main.courses.Course;
import java.util.List;
import java.util.Scanner;

public class Professor extends User {
    private List<Course> assignedCourses;

    public Professor(String name, String email, String password, List<Course> assignedCourses) {
        super(name, email, password);
        this.assignedCourses = assignedCourses;
    }

    @Override
    public void displayMenu() {
        System.out.println("Professor Menu: \n1. View Courses \n2. Manage Courses \n3. View Enrolled Students");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewAssignedCourses();
                break;
            case 2:
                manageCourses();
                break;
            case 3:
                viewEnrolledStudents();
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    public void viewAssignedCourses() {
        if (assignedCourses.isEmpty()) {
            System.out.println("No courses assigned.");
            return;
        }

        for (Course course : assignedCourses) {
            System.out.println("================================");
            System.out.println(course);
            System.out.println("================================");
        }
    }

    public void manageCourses() {
        if (assignedCourses.isEmpty()) {
            System.out.println("No courses to manage.");
            return;
        }

        System.out.println("Select a course to manage:");
        for (int i = 0; i < assignedCourses.size(); i++) {
            System.out.println((i + 1) + ". " + assignedCourses.get(i).getTitle());
        }

        Scanner scanner = new Scanner(System.in);
        int courseChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (courseChoice < 1 || courseChoice > assignedCourses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = assignedCourses.get(courseChoice - 1);
        System.out.println("Managing course: " + selectedCourse.getTitle());

        System.out.println("Select what to update:");
        System.out.println("1. Syllabus\n2. Timings\n3. Credits\n4. Prerequisites\n5. Enrollment Limit\n6. Office Hours");

        int updateChoice = scanner.nextInt();
        scanner.nextLine();

        switch (updateChoice) {
            case 1:
                System.out.println("Enter new syllabus:");
                String syllabus = scanner.nextLine();
                selectedCourse.setSyllabus(syllabus);
                break;
            case 2:
                System.out.println("Enter new timings:");
                String timings = scanner.nextLine();
                selectedCourse.setTimings(timings);
                break;
            case 3:
                System.out.println("Enter new credits:");
                int credits = scanner.nextInt();
                selectedCourse.setCredits(credits);
                break;
            case 4:
                System.out.println("Enter new prerequisites:");
                String prerequisites = scanner.nextLine();
                selectedCourse.setPrerequisites(prerequisites);
                break;
            case 5:
                System.out.println("Enter new enrollment limit:");
                int enrollmentLimit = scanner.nextInt();
                selectedCourse.setEnrollmentLimit(enrollmentLimit);
                break;
            case 6:
                System.out.println("Enter new office hours:");
                String officeHours = scanner.nextLine();
                selectedCourse.setOfficeHours(officeHours);
                break;
            default:
                System.out.println("Invalid selection.");
        }

        System.out.println("Course details updated successfully.");
    }

    public void viewEnrolledStudents() {
        if (assignedCourses.isEmpty()) {
            System.out.println("No courses assigned.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a course to view enrolled students:");
        for (int i = 0; i < assignedCourses.size(); i++) {
            System.out.println((i + 1) + ". " + assignedCourses.get(i).getTitle());
        }

        int courseChoice = scanner.nextInt();
        scanner.nextLine();

        if (courseChoice < 1 || courseChoice > assignedCourses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = assignedCourses.get(courseChoice - 1);
        List<Student> enrolledStudents = selectedCourse.getEnrolledStudents();

        if (enrolledStudents.isEmpty()) {
            System.out.println("No students enrolled in this course.");
        } else {
            System.out.println("Enrolled students in " + selectedCourse.getTitle() + ":");
            for (Student student : enrolledStudents) {
                System.out.println("Name: " + student.getName());
                System.out.println("Email: " + student.getEmail());
                System.out.println("Branch: " + student.getBranchName());
                System.out.println("Academic Standing: " + student.getAcademicStanding());
                System.out.println("------------------------------");
            }
        }
    }
}
