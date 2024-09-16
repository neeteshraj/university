package main.data;

import main.courses.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class CourseData {
    private static final List<Course> courses = new ArrayList<>();

    public static List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static Course getCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public static boolean deleteCourse(String courseCode) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getCourseCode().equals(courseCode)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
