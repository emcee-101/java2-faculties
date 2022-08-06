package de.fherfurt.faculty.data.classes;

import de.fherfurt.faculty.data.classes.enums.CourseType;

import javax.persistence.*;
import java.util.List;

/**
 * Entity-Class for Course
 */
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int numberOfSemesters;
    private float numerusClausus;
    private CourseType typeOfCourse;
    private String directorName;

    @ManyToMany
    private List<Module> modules;

    @ManyToOne
    private Faculty faculty;

    /**
     * Class Constructor
     */
    public Course() {}

    /**
     * Class Constructor to generate Course with Values
     */
    public Course(String name,
                  int numberOfSemesters,
                  float numerusClausus,
                  CourseType typeOfCourse,
                  String directorName,
                  Faculty faculty) {
        this.name = name;
        this.numberOfSemesters = numberOfSemesters;
        this.numerusClausus = numerusClausus;
        this.typeOfCourse = typeOfCourse;
        this.directorName = directorName;
        this.faculty = faculty;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}