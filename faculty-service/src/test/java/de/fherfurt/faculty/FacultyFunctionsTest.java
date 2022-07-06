package de.fherfurt.faculty;

import de.fherfurt.faculty.data.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import de.fherfurt.faculty.data.classes.*;
import de.fherfurt.faculty.data.classes.Module;

class FacultyFunctionsTest {

    TestData testData;

    @BeforeEach
    void init() {
        DaoHolder.getInstance().getCourseDao().deleteAll();
        DaoHolder.getInstance().getFacultyDao().deleteAll();
        DaoHolder.getInstance().getModuleDao().deleteAll();
        DaoHolder.getInstance().getUniversityDao().deleteAll();

        testData = new TestData();
        DaoHolder.getInstance().getCourseDao().create(testData.getCourses());
        DaoHolder.getInstance().getFacultyDao().create(testData.getFaculties());
        DaoHolder.getInstance().getModuleDao().create(testData.getModules());
        DaoHolder.getInstance().getUniversityDao().create(testData.getUniversities());
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
        String newProfessorName = "Schorcht";

        Collection<Module> modules =  DaoHolder.getInstance()
                                               .getModuleDao()
                                               .findAllByFilter("name", "java1");
        Module module = modules.stream()
                               .findFirst()
                               .get();

        long idOfModule = module.getId();
        List<String> oldNameList = module.getProfessorNamesAsList();
        
        // WHEN
        ModuleFunctions.addProfessorToModule(newProfessorName, idOfModule);

        // THEN
        Module updatedModule =  DaoHolder.getInstance()
                                         .getModuleDao()
                                         .findById(idOfModule);

        oldNameList.add(newProfessorName);
    
        assertTrue(oldNameList == updatedModule.getProfessorNamesAsList());

    }

    @Test
    void removeProfessorFromModule() {
        // GIVEN
        String professorNameToDelete = testData.getModules().get(0).getProfessorNames();
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
        assertSame(resultTest1, testData.getFaculties().get(0).getDeanName());
        assertSame(resultTest2, "Faculty not found");
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
        assertTrue(testOutput1);
        assertNotEquals(true, testOutput2);
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
        assertTrue(testOutput1);
        assertNotEquals(true, testOutput2);
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
        assertTrue(testOutput1);
        assertNotEquals(true, testOutput2);
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
        assertTrue(testOutput1);
        assertNotEquals(true, testOutput2);
    }
    
    @Test
    void updateDescriptionDocument() {
        // GIVEN
        String newUrlDescriptionDocument = "new url";
        String moduleName = testData.getModules().get(0).getName();

        assertFalse(moduleRepository.findByName(moduleName).getUrlDescriptionDocument().contains(newUrlDescriptionDocument));
        // WHEN
        facultyProduction.updateDescriptionDocument(newUrlDescriptionDocument, moduleName);
        // THEN
        assertTrue(moduleRepository.findByName(moduleName).getUrlDescriptionDocument().contains(newUrlDescriptionDocument));
    }
}