package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Course;
import de.fherfurt.faculty.data.repository.core.Functions;

import java.util.List;

public class CourseRepository{

    public CourseRepository(List<Course> courses) {
        this.courses = courses;
    }

    private final Functions<Course> courseFunctions = new Functions<Course>();
    private final List<Course> courses;

    public void save(Course course) {
        courseFunctions.save(course, courses);
    }

    public Course findByName(String name) {
        return courseFunctions.findByName(name, courses);
    }

    public void delete(String name) {
        courseFunctions.delete(name, courses);
    }

    public List<Course> getList() {
        return courses;
    }
}
