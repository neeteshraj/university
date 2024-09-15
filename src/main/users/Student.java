package main.users;

import main.courses.Course;
import main.courses.CourseCatalog;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private String branchName;
    private int semester;
    private final List<Course> completedCourses = new ArrayList<>();
    private final List<Course> registeredCourses = new ArrayList<>();
    private int totalCredits = 0;
    private List<Course> currentCourses = new ArrayList<>();

    public Student(String name, String email, String password, String branchName, int semester) {
        super(name, email, password);
        this.branchName = branchName;
        this.semester = semester;
    }

    public List<Course> getCurrentCourses() {
        return currentCourses != null ? currentCourses : new ArrayList<>();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<Course> getCompletedCourses() {
        return completedCourses;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void viewGrades() {
        if (completedCourses.isEmpty()) {
            System.out.println("No completed courses found.");
            return;
        }

        System.out.println("Grades for Completed Courses:");
        for (Course course : completedCourses) {
            System.out.println("Course Code: " + course.getCourseCode() + " | Grade: " + course.getGrade());
        }
    }

    public double calculateSGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Course course : currentCourses) {
            if ("Not Assigned".equals(course.getGrade())) {
                continue;
            }

            double gradePoints = convertGradeToPoints(course.getGrade());
            totalPoints += gradePoints * course.getCredits();
            totalCredits += course.getCredits();
        }

        return totalCredits == 0 ? 0 : totalPoints / totalCredits;
    }

    public double calculateCGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Course course : completedCourses) {
            if ("Not Assigned".equals(course.getGrade())) {
                continue;
            }

            double gradePoints = convertGradeToPoints(course.getGrade());
            totalPoints += gradePoints * course.getCredits();
            totalCredits += course.getCredits();
        }

        double currentSemesterSGPA = calculateSGPA();
        totalPoints += currentSemesterSGPA * getTotalCredits();
        totalCredits += getTotalCredits();

        return totalCredits == 0 ? 0 : totalPoints / totalCredits;
    }

    private double convertGradeToPoints(String grade) {
        switch (grade.toUpperCase()) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0;
        }
    }

    public void viewAvailableCourses() {
        List<Course> courses = CourseCatalog.getCoursesForSemester(this.semester);
        System.out.println("Available Courses for Semester " + this.semester + ":");
        for (Course course : courses) {
            System.out.println("====================================");
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Professor: " + course.getProfessor());
            System.out.println("Credits: " + course.getCredits());
            System.out.println("Prerequisites: " + course.getPrerequisites());
            System.out.println("Timings: " + course.getTimings());
            System.out.println("Location: " + course.getLocation());
            System.out.println("Semester: " + course.getSemester());
            System.out.println("Grade: " + course.getGrade());
            System.out.println("====================================");
        }
    }

    private boolean prerequisitesMet(Course course) {
        String prerequisites = course.getPrerequisites();

        if (prerequisites == null || prerequisites.equalsIgnoreCase("None")) {
            return true;
        }

        String[] requiredCourses = prerequisites.split(",\\s*");

        for (String requiredCourse : requiredCourses) {
            boolean isCompleted = false;

            for (Course completedCourse : completedCourses) {
                if (completedCourse.getCourseCode().equalsIgnoreCase(requiredCourse)) {
                    isCompleted = true;
                    break;
                }
            }

            if (!isCompleted) {
                return false;
            }
        }

        return true;
    }

    public void registerForCourse(String courseCode) {
        viewAvailableCourses();

        List<Course> availableCourses = CourseCatalog.getCoursesForSemester(this.semester);
        Course selectedCourse = null;

        for (Course course : availableCourses) {
            if (course.getCourseCode().equals(courseCode)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse == null) {
            System.out.println("Course not found or not available for this semester.");
            return;
        }

        if (!prerequisitesMet(selectedCourse)) {
            System.out.println("Prerequisites not met for this course.");
            return;
        }

        int newTotalCredits = totalCredits + selectedCourse.getCredits();
        if (newTotalCredits > 20) {
            System.out.println("Credit limit exceeded. You can only register up to 20 credits.");
            return;
        }

        registeredCourses.add(selectedCourse);
        currentCourses.add(selectedCourse);
        totalCredits = newTotalCredits;
        System.out.println("Successfully registered for course: " + selectedCourse.getCourseCode());
    }

    public void dropCourse(String courseCode) {
        Course courseToDrop = null;

        for (Course course : registeredCourses) {
            if (course.getCourseCode().equals(courseCode)) {
                courseToDrop = course;
                break;
            }
        }

        if (courseToDrop == null) {
            System.out.println("Course not found in your registered courses.");
            return;
        }

        registeredCourses.remove(courseToDrop);
        totalCredits -= courseToDrop.getCredits();
        System.out.println("Successfully dropped course: " + courseCode);
    }

    public void completeSemester() {
        if (registeredCourses.isEmpty()) {
            System.out.println("Cannot complete the semester. No courses registered.");
            return;
        }

        for (Course course : registeredCourses) {
            if (course.getGrade().equals("Not Assigned")) {
                System.out.println("Cannot complete the semester. Grade not assigned for course: " + course.getCourseCode());
                return;
            }
        }

        this.semester++;
        completedCourses.addAll(registeredCourses);
        registeredCourses.clear();
        totalCredits = 0;
        System.out.println("Semester completed. Moved to semester " + this.semester);
    }

    public void viewWeeklySchedule() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
            return;
        }

        System.out.println("Weekly Schedule:");
        for (Course course : registeredCourses) {
            System.out.println("====================================");
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Professor: " + course.getProfessor());
            System.out.println("Timings: " + course.getTimings());
            System.out.println("Location: " + course.getLocation());
            System.out.println("====================================");
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("Student Menu");
        System.out.println("1. View Available Courses");
        System.out.println("2. Register for a Course");
        System.out.println("3. Drop a Course");
        System.out.println("4. View Weekly Schedule");
        System.out.println("5. Complete Semester");
        System.out.println("6. View Grades");
        System.out.println("7. Calculate SGPA/CGPA");
        System.out.println("8. Submit Complaint");
        System.out.println("9. View Complaints");
        System.out.println("10. Logout");
    }
}