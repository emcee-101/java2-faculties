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


    public boolean isFacultynameValid(String name) {
        return true; }


// CODE HIER!!!!


    public List<String> filterModulesBySemesterAndCourse (String courseName, int numberOfSemester) {
        List<Module> inputModuleList;
        inputModuleList = moduleRepository.getList();
        List<String> outputModuleList = null;

        for (int i = 0; i < inputModuleList.size(); i++) {
            Module anyModule = inputModuleList.get(i);
            if ((courseName == anyModule.getCourseName()) && (numberOfSemester == anyModule.getSemester())) {
                outputModuleList.add(anyModule.getName());
            }
        }
        return outputModuleList;
        
    }

    public List<String> filterModulesByCourse (String courseName) {
        List<Module> inputModuleList;
        inputModuleList = moduleRepository.getList();
        List<String> outputModuleList = null;

        for (int i = 0; i < inputModuleList.size(); i++) {
            Module anyModule = inputModuleList.get(i);
            if (courseName == anyModule.getCourseName()) {
                outputModuleList.add(anyModule.getName());
            }
        }
        return outputModuleList;

    }

    /*
            BIS: 25. 3. End-Deadline
            Ziel: 20.03.2022
            Meeting: 16.03.2022

    FUNKTIONALITÄTEN TO BE IMPLEMENTED (NAMES!!!!!!, ZU JEDER FUNKTION EINEN TEST!!!!!!!!):

        - alle Module in einem semester in einem bestimmten Kurs            [Falko]

        - alle Module eines Kurses                                          [Falko]

        - is....NameValid (überpfüfung der namen für alle Klassen)          [Sarah]

        - Dekan von bestimmter Fakultät ausgeben                            [Niklas]

        - updateDescriptionDocument (Setter)                                [Marvin]

        - add Professor to Module                                           [Chris]
            - findbyName
            - Professor prüfen  (getProfessorNames)
            - Professor ggf hinzufügen (setProfessorNames)
            - save

        - remove Professor from Module                                      [Chris]

        - Add a Module, Fakultät, Universität, Kurs (also KONSTRUKTOREN!)   [Chris]

        - DELETE a Module, Fakultät, Universität, Kurs (also DESTRUKTOREN!) [Sarah]


    DOKUMENTATION:

        - Introduction (Namen, Bibliotheken, KURZE beschreibung(?))                                                         [Falko]

        - Datenmodell (Erklärung, Klassen (Was? Wie aufgebaut? Beziehungen untereinander?))                                 [Chris]

        - repository-Schicht (Functions.java + Sinn und Zweck, ...Repository.java und wie sie die Functions.java nutzen)    [Niklas]

        - Funktionen (Warum relevant?, SWT Orientiert)                                                                      [FUNKTIONSERSTELLER NACH EIGENEM ERMESSEN]

        - aktualisiertes Klassendiagramm                                                                                    [Marvin]

        - Diagramm der Programmarchitektur                                                                                  [Sarah]

        - use case Diagramme (Funktionalitätenbezogen)                                                                      [FUNKTIONSERSTELLER NACH EIGENEM ERMESSEN]



    Java-Dokumentation Format:

        * saves the entity in the list
        *
        * @param entity object to save
        * @param list the unique list which contains all items
        *




    */
}