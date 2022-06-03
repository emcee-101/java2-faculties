package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Course;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateInstanceTest {

    @org.junit.jupiter.api.Test
    void createInstance(){

        //GIVEN
        TestData test = new TestData();

        List<Course> courseList = test.getCourses();
        Course course1 = courseList.get(0);


        //WHEN
        Course savedCourse = DaoHolder.getInstance().getCourseDao().create(course1);
        long id = savedCourse.getId();


        Course foundCourse = DaoHolder.getInstance().getCourseDao().findById(id);

        //THEN

        assertEquals(savedCourse, foundCourse);


    }




}
