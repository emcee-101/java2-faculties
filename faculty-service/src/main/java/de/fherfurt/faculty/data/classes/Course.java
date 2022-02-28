package de.fherfurt.faculty.data.classes;

import de.fherfurt.faculty.data.classes.core.Basic;
import de.fherfurt.faculty.data.classes.enums.CourseType;

public class Course extends Basic {
    private int numberOfSemesters;
    private float numerusClausus;
    private CourseType typeOfCourse;
    private String directorName;
    private String facultyName;
    
    public Course(String name,
                  int numberOfSemesters,
                  float numerusClausus,
                  CourseType typeOfCourse,
                  String directorName,
                  String facultyName) {
        super(name);

        this.numberOfSemesters = numberOfSemesters;
        this.numerusClausus = numerusClausus;
        this.typeOfCourse = typeOfCourse;
        this.directorName = directorName;
        this.facultyName = facultyName;
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

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}