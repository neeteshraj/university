package main.services;

import main.data.CourseData;
import main.courses.Course;
import main.data.UserData;
import main.users.Student;

import java.util.List;
import java.util.Scanner;

public class AdminService {

    public static void viewStudents() {
        List<Student> students = UserData.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student student : students) {
                System.out.println("====================================");
                System.out.println("Name: " + student.getName());
                System.out.println("Email: " + student.getEmail());
                System.out.println("Branch: " + student.getBranchName());
                System.out.println("Semester: " + student.getSemester());
                System.out.println("====================================");
            }
        }
    }


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

    public static void updateStudentRecord(Scanner scanner) {
        System.out.println("Enter the email of the student to update:");
        String email = scanner.nextLine();

        Student student = UserData.getStudentByEmail(email);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Update Student Record");
        System.out.println("1. Update Branch");
        System.out.println("2. Update Semester");
        System.out.println("3. Update Both");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter new branch:");
                String branch = scanner.nextLine();
                student.setBranchName(branch);
                System.out.println("Branch updated successfully.");
                break;
            case 2:
                System.out.println("Enter new semester:");
                int semester = scanner.nextInt();
                scanner.nextLine();
                student.setSemester(semester);
                System.out.println("Semester updated successfully.");
                break;
            case 3:
                System.out.println("Enter new branch:");
                branch = scanner.nextLine();
                student.setBranchName(branch);

                System.out.println("Enter new semester:");
                semester = scanner.nextInt();
                scanner.nextLine();
                student.setSemester(semester);
                System.out.println("Branch and Semester updated successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void updateStudentGrades(Scanner scanner) {
        System.out.println("Enter the email of the student to update grades:");
        String email = scanner.nextLine();

        Student student = UserData.getStudentByEmail(email);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter the course code for which to update the grade:");
        String courseCode = scanner.nextLine();

        Course courseToUpdate = null;
        for (Course course : student.getCompletedCourses()) {
            if (course.getCourseCode().equals(courseCode)) {
                courseToUpdate = course;
                break;
            }
        }

        if (courseToUpdate == null) {
            System.out.println("Course not found in completed courses.");
            return;
        }

        System.out.println("Enter new grade for the course:");
        String newGrade = scanner.nextLine();

        courseToUpdate.setGrade(newGrade);
        System.out.println("Course grade updated successfully.");
    }


    public static void updateStudentPersonalInfo(Scanner scanner) {
        System.out.println("Enter the email of the student to update personal information:");
        String email = scanner.nextLine();

        Student student = UserData.getStudentByEmail(email);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter new name:");
        String newName = scanner.nextLine();
        student.setName(newName);

        System.out.println("Enter new email:");
        String newEmail = scanner.nextLine();
        student.setEmail(newEmail);

        System.out.println("Student personal information updated successfully.");
    }


    public static void manageStudents(Scanner scanner) {
        while (true) {
            System.out.println("Student Management Menu");
            System.out.println("1. View Students");
            System.out.println("2. Update Students Records");
            System.out.println("3. Update Students Grade");
            System.out.println("4. Update Students Personal Information");
            System.out.println("5. Go Back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewStudents();
                    break;
                case 2:
                    updateStudentRecord(scanner);
                    break;
                case 3:
                    updateStudentGrades(scanner);
                    break;
                case 4:
                    updateStudentPersonalInfo(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
