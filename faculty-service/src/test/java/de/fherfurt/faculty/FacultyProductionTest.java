package de.fherfurt.faculty;

import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacultyProductionTest {

    UniversityRepository universityRepository;
    FacultyRepository facultyRepository;
    CourseRepository courseRepository;
    ModuleRepository moduleRepository;
    FacultyProduction facultyProduction;
    TestData testData;

    @BeforeEach
    void init() {
        // GIVEN
        testData = new TestData();
        universityRepository = new UniversityRepository(testData.getUniversities());
        facultyRepository = new FacultyRepository(testData.getFaculties());
        courseRepository = new CourseRepository(testData.getCourses());
        moduleRepository = new ModuleRepository(testData.getModules());
        facultyProduction = new FacultyProduction(universityRepository, facultyRepository, courseRepository, moduleRepository);
    }

    @Test
    void addNewUniversity() {
        // GIVEN
        String testUniName = "another uni";
        String testUniPresident = "new president";
        assertNull(universityRepository.findByName(testUniName));
        // WHEN
        facultyProduction.addNewUniversity(testUniName, testUniPresident);
        // THEN
        assertNotNull(universityRepository.findByName(testUniName));
    }

    @Test
    void deleteUniversity() {
        // GIVEN
        String testUniName = testData.getUniversities().get(0).getName();

        assertNotNull(universityRepository.findByName(testUniName));
        // WHEN
        facultyProduction.deleteUniversity(testUniName);
        // THEN
        assertNull(universityRepository.findByName(testUniName));
    }

    @Test
    void addProfessorToModule() {
        // GIVEN
        String newProfessorName = "new prof";
        String moduleName = testData.getModules().get(0).getName();

        assertFalse(moduleRepository.findByName(moduleName).getProfessorNames().contains(newProfessorName));
        // WHEN
        facultyProduction.addProfessorToModule(newProfessorName, moduleName);
        // THEN
        assertTrue(moduleRepository.findByName(moduleName).getProfessorNames().contains(newProfessorName));
    }

    @Test
    void removeProfessorFromModule() {
        // GIVEN
        String professorNameToDelete = testData.getModules().get(0).getProfessorNames().get(0);
        String moduleName = testData.getModules().get(0).getName();

        assertTrue(moduleRepository.findByName(moduleName).getProfessorNames().contains(professorNameToDelete));
        // WHEN
        facultyProduction.removeProfessorFromModule(professorNameToDelete, moduleName);
        // THEN
        assertFalse(moduleRepository.findByName(moduleName).getProfessorNames().contains(professorNameToDelete));
    }

    @Test
    void filterModulesBySemesterAndCourse() {
        // given
        String testModuleName = testData.getModules().get(0).getName();
        int testModuleSemester = testData.getModules().get(0).getSemester();
        String testCourseName = testData.getModules().get(0).getCourseName();
        // when
        List<String> testOutput = facultyProduction.filterModulesBySemesterAndCourse(testCourseName, testModuleSemester);
        List<String> testOutput2 = facultyProduction.filterModulesBySemesterAndCourse(testCourseName, testModuleSemester + 1);
        List<String> testOutput3 = facultyProduction.filterModulesBySemesterAndCourse(testCourseName + "test", testModuleSemester);
        // then
        assertTrue(testOutput.contains(testModuleName));
        assertFalse(testOutput2.contains(testModuleName));
        assertFalse(testOutput3.contains(testModuleName));


    }

    @Test
    void filterModulesByCourse() {
        // given
        String testModuleName = testData.getModules().get(0).getName();
        String testCourseName = testData.getModules().get(0).getCourseName();
        // when
        List<String> testOutput = facultyProduction.filterModulesByCourse(testCourseName);
        List<String> testOutput2 = facultyProduction.filterModulesByCourse(testCourseName + "test");
        // then
        assertTrue(testOutput.contains(testModuleName));
        assertFalse(testOutput2.contains(testModuleName));
    }
}