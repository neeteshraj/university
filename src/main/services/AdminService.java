package main.services;

import main.complaints.Complaint;
import main.data.CourseData;
import main.courses.Course;
import main.data.UserData;
import main.users.Student;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

        // New fields
        System.out.println("Enter enrollment limit:");
        int enrollmentLimit = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter syllabus:");
        String syllabus = scanner.nextLine();

        Course course = new Course(courseCode, title, professor, credits, prerequisites, timings, semester, location, enrollmentLimit, syllabus);
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

    public static void handleComplaintManagement(Scanner scanner) {
        while (true) {
            System.out.println("Complaint Management Menu");
            System.out.println("1. View All Complaints");
            System.out.println("2. Filter Complaints by Status");
            System.out.println("3. Filter Complaints by Date");
            System.out.println("4. Resolve Complaint by ID");
            System.out.println("5. Set Complaint to Pending");
            System.out.println("6. Go Back");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    viewAllComplaints();
                    break;
                case "2":
                    filterComplaintsByStatus(scanner);
                    break;
                case "3":
                    filterComplaintsByDate(scanner);
                    break;
                case "4":
                    resolveComplaintById(scanner);
                    break;
                case "5":
                    setComplaintToPending(scanner);
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void filterComplaintsByStatus(Scanner scanner) {
        System.out.println("Enter status to filter by (Pending/Resolved): ");
        String status = scanner.nextLine();
        List<Complaint> filteredComplaints = UserData.filterComplaintsByStatus(status);

        if (filteredComplaints.isEmpty()) {
            System.out.println("No complaints with status: " + status);
        } else {
            filteredComplaints.forEach(AdminService::printComplaintDetails);
        }
    }

    private static void filterComplaintsByDate(Scanner scanner) {
        System.out.println("Enter date to filter by (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateInput);
            List<Complaint> filteredComplaints = UserData.filterComplaintsByDate(date);

            if (filteredComplaints.isEmpty()) {
                System.out.println("No complaints on date: " + date);
            } else {
                filteredComplaints.forEach(AdminService::printComplaintDetails);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter in the format yyyy-MM-dd.");
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
                    assignProfessorToCourse(scanner);
                    break;
                case 5:
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

        List<Course> currentCourses = student.getCurrentCourses();
        if (currentCourses == null) {
            currentCourses = new ArrayList<>();
        }

        for (Course course : student.getCompletedCourses()) {
            if (course.getCourseCode().equals(courseCode)) {
                courseToUpdate = course;
                break;
            }
        }

        if (courseToUpdate == null) {
            for (Course course : currentCourses) {
                if (course.getCourseCode().equals(courseCode)) {
                    courseToUpdate = course;
                    break;
                }
            }
        }

        if (courseToUpdate == null) {
            System.out.println("Course not found in student records. Please verify the course code.");
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

    private static void viewAllComplaints() {
        List<Complaint> complaints = UserData.getAllComplaints();
        if (complaints.isEmpty()) {
            System.out.println("No complaints available.");
        } else {
            for (Complaint complaint : complaints) {
                System.out.println("====================================");
                System.out.println("Complaint ID: " + complaint.getId());
                System.out.println("Student Email: " + complaint.getStudentEmail());
                System.out.println("Description: " + complaint.getDescription());
                System.out.println("Status: " + complaint.getStatus());
                System.out.println("====================================");
            }
        }
    }

    private static void resolveComplaintById(Scanner scanner) {
        System.out.println("Enter the ID of the complaint to resolve:");
        int id = Integer.parseInt(scanner.nextLine());

        Complaint complaint = UserData.getAllComplaints().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (complaint == null) {
            System.out.println("Complaint not found.");
            return;
        }

        System.out.println("Enter the resolution details:");
        String resolutionDetails = scanner.nextLine();

        complaint.setStatus("Resolved");
        complaint.setResolutionDetails(resolutionDetails);

        System.out.println("Complaint resolved successfully.");
    }


    private static void setComplaintToPending(Scanner scanner) {
        System.out.println("Enter the ID of the complaint to set to pending:");
        int id = Integer.parseInt(scanner.nextLine());

        Complaint complaint = UserData.getAllComplaints().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (complaint == null) {
            System.out.println("Complaint not found.");
            return;
        }

        complaint.setStatus("Pending");
        System.out.println("Complaint status updated to pending.");
    }

    public static void assignProfessorToCourse(Scanner scanner) {
        System.out.println("Assign Professor to Course");

        System.out.println("Enter course code:");
        String courseCode = scanner.nextLine();

        Course course = CourseData.getCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.println("Enter professor's name:");
        String professorName = scanner.nextLine();

        // Set the professor to the course
        course.setProfessor(professorName);

        System.out.println("Professor assigned successfully to the course.");
    }

    private static void printComplaintDetails(Complaint complaint) {
        System.out.println("====================================");
        System.out.println("Complaint ID: " + complaint.getId());
        System.out.println("Student Email: " + complaint.getStudentEmail());
        System.out.println("Description: " + complaint.getDescription());
        System.out.println("Status: " + complaint.getStatus());
        System.out.println("Submission Date: " + complaint.getSubmissionDate());
        System.out.println("Resolution Details: " + complaint.getResolutionDetails());
        System.out.println("====================================");
    }
}
