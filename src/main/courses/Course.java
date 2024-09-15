package main.courses;

public class Course {
    private final String courseCode;
    private final String title;
    private final String professor;
    private final int credits;
    private final String prerequisites;
    private final String timings;
    private final int semester;
    private final String location;
    private String grade;


    public Course(String courseCode, String title, String professor, int credits, String prerequisites, String timings, int semester, String location) {
        this.courseCode = courseCode;
        this.title = title;
        this.professor = professor;
        this.credits = credits;
        this.prerequisites = prerequisites;
        this.timings = timings;
        this.semester = semester;
        this.location = location;
        this.grade = "Not Assigned";
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
                "Grade: " + grade;
    }
}
