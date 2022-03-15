package de.fherfurt.faculty;

import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    void outputDekanByFaculty() {
        // GIVEN
        String facultyToGetDekanFrom1 = testData.getFaculties().get(0).getName();
        String facultyToGetDekanFrom2 = "Gartenbau";

        // WHEN
        String resultTest1 = facultyProduction.outputDekanByFaculty(facultyToGetDekanFrom1);
        String resultTest2 = facultyProduction.outputDekanByFaculty(facultyToGetDekanFrom2);

        // THEN
        assertTrue(resultTest1 == (testData.getFaculties().get(0).getDecanName()));
        assertTrue(resultTest2 == "Faculty not found");
    }
}