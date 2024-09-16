package main.courses;

import main.users.Student;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String courseCode;
    private final String title;
    private String professor;
    private int credits;
    private String prerequisites;
    private String timings;
    private final int semester;
    private final String location;
    private String grade;
    private String syllabus;
    private int enrollmentLimit;
    private String officeHours;
    private List<Student> enrolledStudents;

    public Course(String courseCode, String title, String professor, int credits, String prerequisites, String timings, int semester, String location, int enrollmentLimit, String officeHours) {
        this.courseCode = courseCode;
        this.title = title;
        this.professor = professor;
        this.credits = credits;
        this.prerequisites = prerequisites;
        this.timings = timings;
        this.semester = semester;
        this.location = location;
        this.grade = "Not Assigned";
        this.syllabus = "Not Set";
        this.enrollmentLimit = enrollmentLimit;
        this.officeHours = officeHours;
        this.enrolledStudents = new ArrayList<>();
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Student student) {
        if (enrolledStudents.size() < enrollmentLimit) {
            enrolledStudents.add(student);
        } else {
            System.out.println("Enrollment limit reached.");
        }
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getCredits() {
        return credits;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public String getTimings() {
        return timings;
    }

    public String getLocation() {
        return location;
    }

    public int getSemester() {
        return semester;
    }

    public String getGrade() {
        return grade;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        this.enrollmentLimit = enrollmentLimit;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\n" +
                "Title: " + title + "\n" +
                "Professor: " + professor + "\n" +
                "Credits: " + credits + "\n" +
                "Prerequisites: " + prerequisites + "\n" +
                "Timings: " + timings + "\n" +
                "Location: " + location + "\n" +
                "Semester: " + semester + "\n" +
                "Grade: " + grade + "\n" +
                "Syllabus: " + syllabus + "\n" +
                "Enrollment Limit: " + enrollmentLimit + "\n" +
                "Office Hours: " + officeHours;
    }
}
