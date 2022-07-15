package de.fherfurt.faculty.data.repository.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import de.fherfurt.faculty.data.classes.*;
import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.enums.CourseType;
import de.fherfurt.faculty.data.repository.DaoHolder;
import de.fherfurt.faculty.data.repository.TestData;


public class GenericDAOTest {

    TestData testData;
    List<University> savedUniversities;
    List<Faculty> savedFaculties;
    List<Course> savedCourses;    
    List<Module> savedModules;
    
    @BeforeEach
    void init() {
        DaoHolder.getInstance().getCourseDao().deleteAll();
        DaoHolder.getInstance().getFacultyDao().deleteAll();
        DaoHolder.getInstance().getModuleDao().deleteAll();
        DaoHolder.getInstance().getUniversityDao().deleteAll();

        testData = new TestData();
        savedUniversities = new ArrayList<University> (DaoHolder.getInstance().getUniversityDao().create(testData.getUniversities()));
        savedFaculties = new ArrayList<Faculty> (DaoHolder.getInstance().getFacultyDao().create(testData.getFaculties()));
        savedCourses = new ArrayList<Course> (DaoHolder.getInstance().getCourseDao().create(testData.getCourses()));
        savedModules = new ArrayList<Module> (DaoHolder.getInstance().getModuleDao().create(testData.getModules()));
    }

    @Test
    void findAllByFilter(){

        // GIVEN
        long IDforTestFaculty = savedFaculties.get(0).getId();
        Faculty testFaculty = DaoHolder.getInstance().getFacultyDao().findById(IDforTestFaculty);

        Course additionalTestCourse1 = new Course("Bogus", 6, -1.0f, CourseType.BACHELOR, "Pringles", testFaculty); 
        Course savedTestCourse = DaoHolder.getInstance().getCourseDao().create(additionalTestCourse1);
        
        // WHEN
        final Collection<Course> foundCoursesSixSemesters = DaoHolder.getInstance().getCourseDao().findAllByFilter("numberOfSemesters", "6");
        final Collection<Course> foundCoursesFourSemesters = DaoHolder.getInstance().getCourseDao().findAllByFilter("numberOfSemesters", "4");
        final Collection<Course> foundCoursesNamedBogus = DaoHolder.getInstance().getCourseDao().findAllByFilter("name", "Bogus");


        List<String> listOfNamesSix = Collections.<String>emptyList();
        List<String> listOfNamesFour = Collections.<String>emptyList();

        for (Course course: foundCoursesSixSemesters)
            listOfNamesSix.add(course.getName());

        
        for (Course course: foundCoursesFourSemesters)
            listOfNamesFour.add(course.getName());

        // THEN

        List<String> possibleOutcome1 = Arrays.asList(new String[]{"Bogus", "BIW"}); 
        List<String> possibleOutcome2 = Arrays.asList(new String[]{"BIW", "Bogus"});

        assertTrue((listOfNamesSix == possibleOutcome1) || (listOfNamesSix == possibleOutcome2));
        assertTrue(listOfNamesFour.isEmpty());
        assertTrue(savedTestCourse.getId() == foundCoursesNamedBogus.stream().findFirst().get().getId());

    }

    @Test
    void findAll(){

        // WHEN
        List<Module> foundModules = new ArrayList<Module>(DaoHolder.getInstance().getModuleDao().findAll());
        Collections.sort(foundModules);
        Collections.sort(savedModules);


        // THEN
        assertTrue(savedModules.equals(foundModules));
    }

    @Test
    void deleteAll(){

        // WHEN
        DaoHolder.getInstance().getCourseDao().deleteAll();
        final Collection<Course> foundCourses = DaoHolder.getInstance().getCourseDao().findAll();

        // THEN
        assertTrue(foundCourses.isEmpty());

    }


}
