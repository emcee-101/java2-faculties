package de.fherfurt.java1service3;

import de.fherfurt.java1service3.enums.CourseType;

public class Course {
    private String name;
    private int numberOfSemesters;
    private float numerusClausus;
    private CourseType typeOfCourse;
    private String director;
    private String[] modules;

    public void setName(String name) {
        this.name = name;
    }

    public Course(String name, int numberOfSemesters, float numerusClausus, CourseType typeOfCourse, String director, String[] modules) {
        this.name = name;
        this.numberOfSemesters = numberOfSemesters;
        this.numerusClausus = numerusClausus;
        this.typeOfCourse = typeOfCourse;
        this.director = director;
        this.modules = modules;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSemesters() {
        return numberOfSemesters;
    }

    public void setNumberOfSemesters(int numberOfSemesters) {
        this.numberOfSemesters = numberOfSemesters;
    }

    public float getNumerusClausus() {
        return numerusClausus;
    }

    public void setNumerusClausus(float numerusClausus) {
        this.numerusClausus = numerusClausus;
    }

    public CourseType getTypeOfCourse() {
        return typeOfCourse;
    }

    public void setTypeOfCourse(CourseType typeOfCourse) {
        this.typeOfCourse = typeOfCourse;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getModules() {
        return modules;
    }

    public void setModules(String[] modules) {
        this.modules = modules;
    }
}