package de.fherfurt.java1service3;

import de.fherfurt.java1service3.enums.CourseType;

public class Course {

    private String name;
    private int numberOfSemesters;
    private float numerusClausus;
    private CourseType typeOfCourse;
    private String director;
    private String[] modules;
}

    private String getName() {
        return name;
    }

    private int getNumberOfSemesters() {
        return numberOfSemesters;
    }

    private float getNumerusClausus() {
        return numerusClausus;
    }

    private CourseType getTypeOfCourse() {
        return typeOfCourse;
    }

    private String getDirector() {
        return director;
    }

    private void setName(String) {

    }

    private void setNumberOfSemesters (int nOS) {
        nOS = numberOfSemesters;
    }

    private void setNumerusClausus (float NC) {
        NC = numerusClausus;
    }


}