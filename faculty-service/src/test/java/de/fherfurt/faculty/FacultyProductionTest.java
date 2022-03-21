package de.fherfurt.faculty;

import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

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
    void outputDeanByFaculty() {
        // GIVEN
        String facultyToGetDekanFrom1 = testData.getFaculties().get(0).getName();
        String facultyToGetDekanFrom2 = "Gartenbau";

        // WHEN
        String resultTest1 = facultyProduction.outputDeanByFaculty(facultyToGetDekanFrom1);
        String resultTest2 = facultyProduction.outputDeanByFaculty(facultyToGetDekanFrom2);

        // THEN
        assertTrue(resultTest1 == (testData.getFaculties().get(0).getDeanName()));
        assertTrue(resultTest2 == "Faculty not found");
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

    @Test
    void isCourseNameValid() {
        // GIVEN
        String testCourseName1 = testData.getCourses().get(0).getName();
        String testCourseName2 = "false name";
        // WHEN
        boolean testOutput1 = facultyProduction.isCourseNameValid(testCourseName1);
        boolean testOutput2 = facultyProduction.isCourseNameValid(testCourseName2);
        // THEN
        assertTrue(testOutput1 == true);
        assertFalse(testOutput2 == true);
    }

    @Test
    void isFacultyNameValid() {
        // GIVEN
        String testFacultyName1 = testData.getFaculties().get(0).getName();
        String testFacultyName2 = "false name";
        // WHEN
        boolean testOutput1 = facultyProduction.isFacultyNameValid(testFacultyName1);
        boolean testOutput2 = facultyProduction.isFacultyNameValid(testFacultyName2);
        // THEN
        assertTrue(testOutput1 == true);
        assertFalse(testOutput2 == true);
    }

    @Test
    void isModuleNameValid() {
        // GIVEN
        String testModuleName1 = testData.getModules().get(0).getName();
        String testModuleName2 = "false name";
        // WHEN
        boolean testOutput1 = facultyProduction.isModuleNameValid(testModuleName1);
        boolean testOutput2 = facultyProduction.isModuleNameValid(testModuleName2);
        // THEN
        assertTrue(testOutput1 == true);
        assertFalse(testOutput2 == true);
    }

    @Test
    void isUniversityNameValid() {
        // GIVEN
        String testUniversityName1 = testData.getUniversities().get(0).getName();
        String testUniversityName2 = "false name";
        // WHEN
        boolean testOutput1 = facultyProduction.isUniversityNameValid(testUniversityName1);
        boolean testOutput2 = facultyProduction.isUniversityNameValid(testUniversityName2);
        // THEN
        assertTrue(testOutput1 == true);
        assertFalse(testOutput2 == true);
    }

}