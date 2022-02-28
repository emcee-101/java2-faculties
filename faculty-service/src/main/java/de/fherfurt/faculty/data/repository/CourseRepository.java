package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Course;
import de.fherfurt.faculty.data.repository.core.Functions;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository{

    private final Functions<Course> courseFunctions = new Functions<Course>();
    private final List<Course> courses = new ArrayList<Course>();

    public void save(Course course) {
        courseFunctions.save(course, courses);
    }

    public void findByName(String name) {
        courseFunctions.findByName(name, courses);
    }

    public void delete(String name) {
        courseFunctions.delete(name, courses);
    }

    public List<Course> getList() {
        return courses;
    }
}
