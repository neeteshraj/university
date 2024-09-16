package main.data;

import main.courses.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class CourseData {
    private static final List<Course> courses = new ArrayList<>();

    static {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Dr. Alice Brown", 4, "None", "MWF 9:00-10:00", 1, "Room 101", 30, "MW 10:00-11:00"));
        courses.add(new Course("MATH201", "Advanced Calculus", "Dr. Bob White", 3, "Calculus I", "TTh 11:00-12:30", 2, "Room 202", 25, "TTh 1:00-2:00"));
        courses.add(new Course("PHYS301", "Quantum Physics", "Dr. Carol Green", 4, "Physics I", "MWF 2:00-3:30", 3, "Room 303", 20, "WF 3:30-4:30"));
        courses.add(new Course("CS201", "Data Structures", "Dr. Alice Brown", 4, "CS101", "TTh 10:00-11:30", 2, "Room 104", 35, "TTh 1:30-2:30"));
        courses.add(new Course("MATH101", "Linear Algebra", "Dr. Bob White", 3, "None", "MWF 1:00-2:00", 1, "Room 204", 40, "MW 2:30-3:30"));
    }

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
