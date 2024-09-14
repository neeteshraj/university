package main.courses;

import main.data.CourseData;
import java.util.List;
import java.util.ArrayList;

public class CourseCatalog {
    public static List<Course> getCoursesForSemester(int semester) {
        List<Course> allCourses = CourseData.getCourses();
        List<Course> result = new ArrayList<>();
        for (Course course : allCourses) {
            if (course.getSemester() == semester) {
                result.add(course);
            }
        }
        return result;
    }
}
