package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Course;
import de.fherfurt.faculty.data.classes.enums.CourseType;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CourseRepositoryTest {

    @org.junit.jupiter.api.Test
    void findByName() {
        // GIVEN
        final Course course1 = new Course(
                "ai",
                7,
                -1,
                CourseType.BACHELOR,
                "Herwig",
                "AI-GET");
        final Course course2 = new Course(
                "biw",
                7,
                -1,
                CourseType.BACHELOR,
                "Meister",
                "KA");
        final List<Course> courses = new ArrayList<Course>(Arrays.asList(course1, course2));
        final String testString1 = course2.getName();
        final String testString2 = "lol";
        final CourseRepository courseRepository = new CourseRepository(courses);

        // WHEN
        Course result1 = courseRepository.findByName(testString1);
        Course result2 = courseRepository.findByName(testString2);

        // THEN
        assertEquals(result1, course2);
        assertNull(result2);
    }
}