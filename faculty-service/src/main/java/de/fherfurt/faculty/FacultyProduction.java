package de.fherfurt.faculty;

import de.fherfurt.faculty.data.classes.Course;
import de.fherfurt.faculty.data.classes.Faculty;
import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.classes.enums.CourseType;
import de.fherfurt.faculty.data.classes.enums.ModuleCertificationType;
import de.fherfurt.faculty.data.classes.enums.ModuleType;
import de.fherfurt.faculty.data.repository.CourseRepository;
import de.fherfurt.faculty.data.repository.FacultyRepository;
import de.fherfurt.faculty.data.repository.ModuleRepository;
import de.fherfurt.faculty.data.repository.UniversityRepository;

import java.util.ArrayList;
import java.util.List;

public class FacultyProduction {

    public FacultyProduction(UniversityRepository universityRepository, FacultyRepository facultyRepository, CourseRepository courseRepository, ModuleRepository moduleRepository) {
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
    }

    UniversityRepository universityRepository;
    FacultyRepository facultyRepository;
    CourseRepository courseRepository;
    ModuleRepository moduleRepository;

    /*
     *
     * filters an inputModuleList with specified parameters and returns the names of fitting modules in an outputModuleList
     *
     * @param courseName name of the course in which the module takes place
     * @param numberOfSemester the semester in which the module takes place
     *
     */
    public List<String> filterModulesBySemesterAndCourse (String courseName, int numberOfSemester) {
        List<Module> inputModuleList;
        inputModuleList = moduleRepository.getList();
        List<String> outputModuleList = new ArrayList<String>();

        for (Module anyModule : inputModuleList) {
            if ((courseName.equals(anyModule.getCourseName())) && (numberOfSemester == anyModule.getSemester())) {
                outputModuleList.add(anyModule.getName());
            }
        }
        return outputModuleList;
    }

    /*
     *
     * filters an inputModuleList with a specified parameter and returns the names of fitting modules in an outputModuleList
     *
     * @param courseName name of the course in which the module takes place
     *
     */
    public List<String> filterModulesByCourse (String courseName) {
        List<Module> inputModuleList;
        inputModuleList = moduleRepository.getList();
        List<String> outputModuleList = new ArrayList<String>();

        for (Module anyModule : inputModuleList) {
            if (courseName.equals(anyModule.getCourseName())) {
                outputModuleList.add(anyModule.getName());
            }
        }
        return outputModuleList;
    }

    // adds a new university to the repository, requires all properties of a university class
    public void addNewUniversity(String name, String presidentName) {
        University university = new University(name, presidentName);
        universityRepository.save(university);
    }

    // deletes a university from the repository by name
    public void deleteUniversity(String universityName) {
        universityRepository.delete(universityName);
    }

    // adds a new faculty to the repository, requires all properties of a faculty class
    public void addNewFaculty(String decanName, String name, String universityName) {
        Faculty faculty = new Faculty(decanName, name, universityName);
        facultyRepository.save(faculty);
    }

    // deletes a faculty from the repository by name
    public void deleteFaculty(String facultyName) {
        facultyRepository.delete(facultyName);
    }

    // adds a new course to the repository, requires all properties of a course class
    public void addNewCourse(String name, int numberOfSemesters, float numerusClausus, CourseType typeOfCourse, String directorName, String facultyName) {
        Course course = new Course(name, numberOfSemesters, numerusClausus, typeOfCourse, directorName, facultyName);
        courseRepository.save(course);
    }

    // deletes a course from the repository by name
    public void deleteCourse(String courseName) {
        courseRepository.delete(courseName);
    }

    // adds a new module to the repository, requires all properties of a module class
    public void addNewModule(String name, int semester, List<String> professorNames, ModuleType typeOfModule, String urlDescriptionDocument, ModuleCertificationType typeOfCertification, String courseName) {
        Module module = new Module(name, semester, professorNames, typeOfModule, urlDescriptionDocument, typeOfCertification, courseName);
        moduleRepository.save(module);
    }

    // deletes a Module from the repository by name
    public void deleteModule(String moduleName) {
        moduleRepository.delete(moduleName);
    }

    /*
     *
     * adds a ProfessorName to the Module-ProfessorNames
     *
     * @param professorName professors name to add
     * @param moduleName module name, in which the professor name will be saved
     *
     */
    public void addProfessorToModule(String professorName, String moduleName) {
        Module module = moduleRepository.findByName(moduleName);
        if (!module.getProfessorNames().contains(professorName)) {
            List<String> newProfessorNames = module.getProfessorNames();
            newProfessorNames.add(professorName);
            module.setProfessorNames(newProfessorNames);
            moduleRepository.save(module);
        }

    }

    /*
     *
     * deletes a ProfessorName from the Module-ProfessorNames
     *
     * @param professorName professors name to delete
     * @param moduleName module name, from which the professor name will be deleted
     *
     */
    public void removeProfessorFromModule(String professorName, String moduleName) {
        Module module = moduleRepository.findByName(moduleName);
        if (module.getProfessorNames().contains(professorName)) {
            List<String> newProfessorNames = module.getProfessorNames();
            newProfessorNames.remove(professorName);
            module.setProfessorNames(newProfessorNames);
            moduleRepository.save(module);
        }
    }

    /*
     *
     * returns the Name of the Head of this faculty
     *
     * @param facultyName name of faculty whose leader is to be searched
     *
     */
   public String outputDeanByFaculty(String facultyName){

        Faculty desiredFaculty = facultyRepository.findByName(facultyName);

        String dean;

        if (desiredFaculty == null){
            dean = "Faculty not found";
        }
        else{
            dean = desiredFaculty.getDeanName();
        }

        return dean;
    }


    /*
     *
     * updating the DescriptionDocument
     *
     * @param urlDescriptionDocument Document to update
     * @param moduleName module name, in which the Document will be saved
     *
     */
   public void updateDescriptionDocument(String urlDescriptionDocument, String moduleName){
        Module module = moduleRepository.findByName(moduleName);
        if (!module.getUrlDescriptionDocument().equals(urlDescriptionDocument)) {
            module.setUrlDescriptionDocument(urlDescriptionDocument);
            moduleRepository.save(module);
        }
    }

    /*
     *
     * validates the name of a course
     *
     * @param courseName: the name of the course, that is to be validated
     *
     */
    public boolean isCourseNameValid(String courseName) {
       Course course = courseRepository.findByName(courseName);
       return course != null;
    }

    /*
     *
     * validates the name of a faculty
     *
     * @param facultyName: the name of the faculty, that is to be validated
     *
     */
    public boolean isFacultyNameValid(String facultyName) {
        Faculty faculty = facultyRepository.findByName(facultyName);
        return faculty != null;
    }

    /*
     *
     * validates the name of a module
     *
     * @param moduleName: the name of the module, that is to be validated
     *
     */
    public boolean isModuleNameValid(String moduleName) {
        Module module = moduleRepository.findByName(moduleName);
        return module != null;
    }

    /*
     *
     * validates the name of a university
     *
     * @param moduleName: the name of the university, that is to be validated
     *
     */
    public boolean isUniversityNameValid(String universityName) {
        University university = universityRepository.findByName(universityName);
        return university != null;
    }
}