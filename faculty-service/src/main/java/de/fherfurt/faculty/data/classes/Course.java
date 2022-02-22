package de.fherfurt.faculty.data.classes;

import de.fherfurt.faculty.data.enums.CourseType;

public class Course {
    private String name;
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
        this.name = name;
        this.numberOfSemesters = numberOfSemesters;
        this.numerusClausus = numerusClausus;
        this.typeOfCourse = typeOfCourse;
        this.directorName = directorName;
        this.facultyName = facultyName;
    }

    /*
     additional functions to add: addDirector(), removeDirector()   change function
                                  addNC(), removeNC()
                                  addTOC(), removeTOC()
                                  changeName()
                                  addNOS(), removeNOS()

    */

    public String getName() {
        return name;
    }

    public int getNumberOfSemesters() {
        return numberOfSemesters;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}