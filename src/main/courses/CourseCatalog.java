package main.courses;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {
    private static List<Course> courses = new ArrayList<>();

    static {
        courses.add(new Course("CS101", "Introduction to Programming", "Dr. Smith", 4, "None", "MWF 10:00-11:00",  1,"Room 101"));
        courses.add(new Course("CS102", "Data Structures", "Dr. Johnson", 4, "CS101", "TTh 09:00-10:30", 2, "Room 102"));
    }

    public static List<Course> getCoursesForSemester(int semester) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getSemester() == semester) {
                result.add(course);
            }
        }
        return result;
    }
}
