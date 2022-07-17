package de.fherfurt.faculty;

import de.fherfurt.faculty.data.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.*;

import de.fherfurt.faculty.data.classes.*;
import de.fherfurt.faculty.data.classes.Module;

class FacultyFunctionsTest {

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
        savedUniversities = new ArrayList<University>(DaoHolder.getInstance().getUniversityDao().create(testData.getUniversities()));
        savedFaculties = new ArrayList<Faculty> (DaoHolder.getInstance().getFacultyDao().create(testData.getFaculties()));
        savedCourses = new ArrayList<Course> (DaoHolder.getInstance().getCourseDao().create(testData.getCourses()));
        savedModules = new ArrayList<Module> (DaoHolder.getInstance().getModuleDao().create(testData.getModules()));
    }


    @Test
    void addProfessorToModule() {

        // GIVEN
        String newProfessorName = "Schorcht";

        Module testModule = savedModules.get(0);

        long idOfModule = testModule.getId();
        LinkedList<String> oldNameList = new LinkedList<String>(testModule.getProfessorNamesAsList());
        assertIterableEquals(new ArrayList<String>(oldNameList), new ArrayList<String>(DaoHolder.getInstance().getModuleDao().findById(idOfModule).getProfessorNamesAsList()));
        
        // WHEN
        ModuleFunctions.addProfessorToModule(newProfessorName, idOfModule);

        // THEN
        Module updatedModule =  DaoHolder.getInstance()
                                         .getModuleDao()
                                         .findById(idOfModule);

        oldNameList.add(newProfessorName);

        assertIterableEquals(new ArrayList<String>(oldNameList), updatedModule.getProfessorNamesAsList());

    }

    @Test
    void removeProfessorFromModule() {

        // GIVEN
        String professorNameToDelete = "prof1";

        Collection<Module> modules =  DaoHolder.getInstance().getModuleDao().findAllByFilter("name", "java1");

        Module module = modules.stream().findFirst().get();

        long idOfModule = module.getId();
        LinkedList<String> oldNameList = new LinkedList<String>(module.getProfessorNamesAsList());

        // WHEN
        ModuleFunctions.removeProfessorFromModule(professorNameToDelete, idOfModule);

        Module updatedModule =  DaoHolder.getInstance().getModuleDao().findById(idOfModule);

        oldNameList.remove(professorNameToDelete);
        Collections.sort(oldNameList);

        LinkedList<String> updatedList = new LinkedList<String>(updatedModule.getProfessorNamesAsList());
        Collections.sort(updatedList);

        // THEN

        assertIterableEquals(oldNameList, updatedModule.getProfessorNamesAsList());
    }


    @Test
    void outputDeanByFaculty() {
        // GIVEN
        Faculty facultyData = savedFaculties.get(0);
        String givenDeanName = facultyData.getDeanName();
        long givenFacultyId = facultyData.getId();

        // WHEN
        String testResult1 = FacultyFunctions.outputDeanByFaculty(givenFacultyId);

        // THEN
        assertSame(testResult1, givenDeanName);
    }
/*
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
*/

    @Test
    void updateDescriptionDocument() {
        // GIVEN
        String newUrlDescriptionDocument = "new url";

        Collection<Module> modules = DaoHolder.getInstance().getModuleDao().findAllByFilter("name","java1");
        Module module = modules.stream().findFirst().get();
        long idOfModule = module.getId();
        String oldDescriptionDocument = module.getUrlDescriptionDocument();

        // WHEN
        ModuleFunctions.updateDescriptionDocument(newUrlDescriptionDocument, idOfModule);

        // THEN
        Module updatedModule = DaoHolder.getInstance().getModuleDao().findById(idOfModule);

        assertSame(updatedModule.getUrlDescriptionDocument(), newUrlDescriptionDocument);
        assertNotSame(updatedModule.getUrlDescriptionDocument(), oldDescriptionDocument);

    }
}