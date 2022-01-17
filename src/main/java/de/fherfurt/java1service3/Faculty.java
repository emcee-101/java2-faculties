package de.fherfurt.java1service3;
import java.util.*;

public class Faculty {

    public Faculty(Person decan, String name, Course[] courses) {
        this.decan = decan;
        this.name = name;
        this.courses = courses;
    }

    private Person decan;
    private String name;
    private List<Course> courses;

    public static void main(String[] args) {
        System.out.println("hello world!");
    }

    public Person getDecan() {
        return decan;
    }

    public void setDecan(Person decan) {
        this.decan = decan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourses(Course course) {
        this.courses.add(course);
    }
}