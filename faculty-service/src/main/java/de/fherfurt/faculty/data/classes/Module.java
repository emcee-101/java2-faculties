package de.fherfurt.faculty.data.classes;

import de.fherfurt.faculty.data.classes.enums.ModuleCertificationType;
import de.fherfurt.faculty.data.classes.enums.ModuleType;

import javax.persistence.*;
import java.util.*;


/**
 *This class represents the data of the different faculties including their courses and the dean name.
 *The properties are adapted to the special requirements of the class.
 *This is where the set- and get- functions for each property can be found.
 *This class also is expanded by enums, for some properties which need options as values.
 */
@Entity
@Table(name = "module")
public class Module implements Comparable<Module>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int semester;
    private String professorNames;
    private ModuleType typeOfModule;
    private String urlDescriptionDocument;
    private ModuleCertificationType typeOfCertification;

    @ManyToMany( mappedBy = "modules")
    private List<Course> courses;

    /**
     * Class Constructor
     */
    public Module() {}

    /**
     * Class Constructor to generate Module with Values
     */
    public Module(String name,
                  int semester,
                  String professorNames,
                  ModuleType typeOfModule,
                  String urlDescriptionDocument,
                  ModuleCertificationType typeOfCertification,
                  List<Course> courses){
        this.name = name;
        this.semester = semester;
        this.professorNames = professorNames;
        this.typeOfModule = typeOfModule;
        this.urlDescriptionDocument = urlDescriptionDocument;
        this.typeOfCertification = typeOfCertification;
        this.courses = courses;
    };

    /**
     * Compares two Modules comparing the IDs
     *
     * @param arg0  Module to Compare
     */
    @Override
    public int compareTo(Module arg0) {

        int returnVal;

        if (this.id < arg0.getId()){
            returnVal = -1;
        } else if (this.id > arg0.getId()){
            returnVal = 1;
        } else
            returnVal = 0;

        return returnVal;
    }

    /**
     * Returns the ProfessorNames as a List of Strings
     */
    public List<String> getProfessorNamesAsList() {

        String temp = this.professorNames;
        List<String> professors;

        String[] result = temp.split(",");

        professors = Arrays.asList(result);

        return professors;
    }

    /**
     * Adds a new ProfessorName to the ProfessorNames of the Faculty
     *
     * @param professorName  ProfessorName to add
     */
    public void addProfessorName(String professorName) {

        String temp = this.professorNames;

        if (temp.isEmpty()){
            // for example: "Haußen,"
            temp = professorName+',';
        }
        else{
            // for example: "Haußen,Schorcht,"
            temp = temp + professorName + ',';
        }

        this.professorNames = temp;

    }

    /**
     * Removes a ProfessorName from ProfessorNames of the Faculty
     *
     * @param professorName ProfessorName to remove
     */
    public void removeProfessorName(String professorName){

        String temp = this.professorNames;

        temp = temp.replace(professorName+",", "");

        this.professorNames = temp;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getProfessorNames() {
        return professorNames;
    }

    public void setProfessorNames(String professorNames) {
        this.professorNames = professorNames;
    }

    public ModuleType getTypeOfModule() {
        return typeOfModule;
    }

    public void setTypeOfModule(ModuleType typeOfModule) {
        this.typeOfModule = typeOfModule;
    }

    public String getUrlDescriptionDocument() {
        return urlDescriptionDocument;
    }

    public void setUrlDescriptionDocument(String urlDescriptionDocument) {
        this.urlDescriptionDocument = urlDescriptionDocument;
    }

    public ModuleCertificationType getTypeOfCertification() {
        return typeOfCertification;
    }

    public void setTypeOfCertification(ModuleCertificationType typeOfCertification) {
        this.typeOfCertification = typeOfCertification;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
