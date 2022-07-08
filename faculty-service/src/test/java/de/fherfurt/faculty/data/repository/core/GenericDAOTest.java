package de.fherfurt.faculty.data.repository.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import de.fherfurt.faculty.data.classes.*;
import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.enums.CourseType;
import de.fherfurt.faculty.data.classes.enums.ModuleCertificationType;
import de.fherfurt.faculty.data.classes.enums.ModuleType;
import de.fherfurt.faculty.data.repository.DaoHolder;
import de.fherfurt.faculty.data.repository.TestData;


public class GenericDAOTest {

    TestData testData;

    @BeforeEach
    void init() {
        DaoHolder.getInstance().getCourseDao().deleteAll();
        DaoHolder.getInstance().getFacultyDao().deleteAll();
        DaoHolder.getInstance().getModuleDao().deleteAll();
        DaoHolder.getInstance().getUniversityDao().deleteAll();

        testData = new TestData();

    }

    @Test
    void findAllByFilter(){

        // GIVEN

        DaoHolder.getInstance().getFacultyDao().create(testData.getFaculties());
        DaoHolder.getInstance().getCourseDao().create(testData.getCourses());

        Course additionalTestCourse1 = new Course("Bogus", 6, true, CourseType.BACHELOR, "Pringles", faculty, modules); 
        DaoHolder.getInstance().getCourseDao().create(additionalTestCourse1);
        
        // WHEN
        final Collection<Course> foundCoursesSixSemesters = DaoHolder.getInstance().getCourseDao().findAllByFilter("numberOfSemesters", "6");
        final Collection<Course> foundCoursesFourSemesters = DaoHolder.getInstance().getCourseDao().findAllByFilter("numberOfSemesters", "4");


        List<String> listOfNamesSix = null;
        List<String> listOfNamesFour = null;

        for (Course course: foundCoursesSixSemesters)
            listOfNamesSix.add(course.getName());

        
        for (Course course: foundCoursesFourSemesters)
            listOfNamesFour.add(course.getName());

        // THEN

        List<String> possibleOutcome1 = Arrays.asList(new String[]{"Bogus", "BIW"}); 
        List<String> possibleOutcome2 = Arrays.asList(new String[]{"BIW", "Bogus"});

        assertTrue((listOfNamesSix == possibleOutcome1) || (listOfNamesSix == possibleOutcome2));
        assertNull(listOfNamesFour);

    }

    @Test
    void findAll(){

        // GIVEN
        final Collection<Course> allCourses = DaoHolder.getInstance().getCourseDao().create(testData.getCourses());

        // WHEN
        final Collection<Course> foundCourses = DaoHolder.getInstance().getCourseDao().findAll();

        // THEN
        assertSame(allCourses, foundCourses);
    }

    @Test
    void deleteAll(){
        // GIVEN
        DaoHolder.getInstance().getCourseDao().create(testData.getCourses());

        // WHEN
        DaoHolder.getInstance().getCourseDao().deleteAll();
        final Collection<Course> foundCourses = DaoHolder.getInstance().getCourseDao().findAll();

        // THEN
        assertNull(foundCourses);

    }


}
