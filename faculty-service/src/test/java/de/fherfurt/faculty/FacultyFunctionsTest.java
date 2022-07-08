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
        String professorNameToDelete = "prof1";

        Collection<Module> modules =  DaoHolder.getInstance().getModuleDao().findAllByFilter("name", "java1");

        Module module = modules.stream().findFirst().get();

        long idOfModule = module.getId();
        List<String> oldNameList = module.getProfessorNamesAsList();

        // WHEN
        ModuleFunctions.removeProfessorFromModule(professorNameToDelete, idOfModule);

        // THEN
        Module updatedModule =  DaoHolder.getInstance().getModuleDao().findById(idOfModule);

        oldNameList.remove(professorNameToDelete);

        assertFalse(oldNameList == updatedModule.getProfessorNamesAsList());
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