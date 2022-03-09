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
        final TestData testData = new TestData();
        final List<Course> courses = testData.getCourses();

        final int indexOfTestCourse = 1;
        final String testString1 = courses.get(indexOfTestCourse).getName();
        final String testString2 = "lol";
        final CourseRepository courseRepository = new CourseRepository(courses);

        // WHEN
        Course result1 = courseRepository.findByName(testString1);
        Course result2 = courseRepository.findByName(testString2);

        // THEN
        assertEquals(result1, courses.get(indexOfTestCourse));
        assertNull(result2);
    }
}