package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Course;
import de.fherfurt.faculty.data.classes.enums.CourseType;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CourseRepositoryTest {

    @org.junit.jupiter.api.Test
    void findByName() {
        // create
        Course course1 = new Course(
                "ai",
                7,
                -1,
                CourseType.BACHELOR,
                "Herwig",
                "AI-GET");
        Course course2 = new Course(
                "biw",
                7,
                -1,
                CourseType.BACHELOR,
                "Meister",
                "KA");
        List<Course> courses = new ArrayList<Course>(Arrays.asList(course1, course2));
        String testString1 = "biw";
        String testString2 = "lol";

        // functions

        //
    }
}