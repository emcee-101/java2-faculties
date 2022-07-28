package de.fherfurt.faculty.data.server.resources;


import de.fherfurt.faculty.data.classes.Course;
import de.fherfurt.faculty.data.classes.Faculty;
import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.classes.enums.CourseType;
import de.fherfurt.faculty.data.classes.enums.ModuleCertificationType;
import de.fherfurt.faculty.data.classes.enums.ModuleType;
import de.fherfurt.faculty.data.repository.DaoHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {

    private final List<University> universities = new ArrayList<University>(Arrays.asList(
            new University(
                    "FH-Erfurt",
                    "president"
            ),
            new University(
                    "UNI-Erfurt",
                    "uni president"
            )
    ));


    private final List<Faculty> faculties = new ArrayList<Faculty>(Arrays.asList(
            new Faculty(
                    "decan1",
                    "KA",
                    universities.get(0)
            ),
            new Faculty(
                    "decan2",
                    "AI-GET",
                    universities.get(0)
            ),
            new Faculty(
                    "decan3",
                    "SOZI",
                    universities.get(0)
            )
    ));


    private final List<Course> courses = new ArrayList<Course>(Arrays.asList(
            new Course(
                    "BIW",
                    6,
                    -1.0f,
                    CourseType.BACHELOR,
                    "Meister",
                    faculties.get(0)
            ),
            new Course(
                    "AI",
                    7,
                    -1.0f,
                    CourseType.BACHELOR,
                    "Herwig",
                    faculties.get(1)
            )
    ));


    private final List<Module> modules = new ArrayList<Module>(Arrays.asList(
            new Module(
                    "java1",
                    3,
                    "prof1,prof3,",
                    ModuleType.COMPULSORY,
                    "doc1.pdf",
                    ModuleCertificationType.PROJECT,
                    new ArrayList<Course>(List.of(courses.get(1)))            ),
            new Module(
                    "mi",
                    5,
                    "prof1,prof2,",
                    ModuleType.SPECIALIZATION,
                    "doc2.pdf",
                    ModuleCertificationType.EXAMANDPROJECT,
                    new ArrayList<Course>(List.of(courses.get(1)))
            )
    ));


    private List<University> savedUniversities;
    private List<Faculty> savedFaculties;
    private List<Course> savedCourses;
    private List<Module> savedModules;

    public List<Course> getCourses() {
        return savedCourses;
    }

    public List<Faculty> getFaculties() {
        return savedFaculties;
    }

    public List<Module> getModules() {
        return savedModules;
    }

    public List<University> getUniversities() {
        return savedUniversities;
    }



    public static TestData getTestData(){
        return new TestData();
    }


    public TestData(){
        DaoHolder.getInstance().getCourseDao().deleteAll();
        DaoHolder.getInstance().getFacultyDao().deleteAll();
        DaoHolder.getInstance().getModuleDao().deleteAll();
        DaoHolder.getInstance().getUniversityDao().deleteAll();

        savedUniversities = new ArrayList<University> (DaoHolder.getInstance().getUniversityDao().create(universities));
        savedFaculties = new ArrayList<Faculty> (DaoHolder.getInstance().getFacultyDao().create(faculties));
        savedCourses = new ArrayList<Course> (DaoHolder.getInstance().getCourseDao().create(courses));
        savedModules = new ArrayList<Module> (DaoHolder.getInstance().getModuleDao().create(modules));
    }

}
