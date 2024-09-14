package main.records;

import main.courses.Course;
import java.util.List;

public class AcademicRecord {
    private List<Course> completedCourses;
    private List<String> grades;

    public void addCompletedCourse(Course course, String grade) {
        completedCourses.add(course);
        grades.add(grade);
    }

    public void calculateGPA() {
        // Calculate GPA logic
    }
}
