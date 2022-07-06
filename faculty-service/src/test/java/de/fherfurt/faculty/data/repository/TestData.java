package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Course;
import de.fherfurt.faculty.data.classes.Faculty;
import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.classes.enums.CourseType;
import de.fherfurt.faculty.data.classes.enums.ModuleCertificationType;
import de.fherfurt.faculty.data.classes.enums.ModuleType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestData {

    private final List<Module> modules = new ArrayList<Module>(Arrays.asList(
            new Module(
                    "java1",
                    3,
                    "prof1,prof3,",
                    ModuleType.COMPULSORY,
                    "doc1.pdf",
                    ModuleCertificationType.PROJECT,
                    "ai"
            ),
            new Module(
                    "mi",
                    5,
                    "prof1,prof2,",
                    ModuleType.SPECIALIZATION,
                    "doc2.pdf",
                    ModuleCertificationType.EXAMANDPROJECT,
                    "ai"
            )
    ));

    private final List<Course> courses = new ArrayList<Course>(Arrays.asList(
            new Course(
                    "BIW",
                    7,
                    -1,
                    CourseType.BACHELOR,
                    "Meister",
                    "KA",
                    new ArrayList<Module>(List.of(modules.get(1)))
            ),
            new Course(
                    "AI",
                    7,
                    -1,
                    CourseType.BACHELOR,
                    "Herwig",
                    "AI-GET",
                    new ArrayList<Module>(List.of())
            )
    ));

    private final List<Faculty> faculties = new ArrayList<Faculty>(Arrays.asList(
            new Faculty(
                    "decan1",
                    "KA",
                    new ArrayList<Course>(List.of(courses.get(0)))
            ),
            new Faculty(
                    "decan2",
                    "AI-GET",
                    new ArrayList<Course>(List.of(courses.get(1)))
            ),
            new Faculty(
                    "decan3",
                    "SOZI",
                    new ArrayList<Course>(List.of(courses.get(2)))
            )
    ));

    private final List<University> universities = new ArrayList<University>(Arrays.asList(
            new University(
                    "FH-Erfurt",
                    "president",
                    new ArrayList<Faculty>(List.of(faculties.get(0), faculties.get(1)))
            ),
            new University(
                    "UNI-Erfurt",
                    "uni president",
                    new ArrayList<Faculty>(List.of(faculties.get(2)))
            )
    ));

    public List<Course> getCourses() {
        return courses;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<University> getUniversities() {
        return universities;
    }
}
