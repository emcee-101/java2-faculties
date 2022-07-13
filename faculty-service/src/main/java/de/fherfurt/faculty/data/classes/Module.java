package de.fherfurt.faculty.data.classes;


import de.fherfurt.faculty.data.classes.enums.ModuleCertificationType;
import de.fherfurt.faculty.data.classes.enums.ModuleType;

import javax.persistence.*;
import java.util.*;

import java.io.*;



@Entity
@Table(name = "module")
public class Module implements Comparable<Module>{

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Module() {

    }


    public Long getId() {
        return id;
    }


    private int semester;
    private String professorNames;
    private ModuleType typeOfModule;
    private String urlDescriptionDocument;
    private ModuleCertificationType typeOfCertification;
    private String name;

    @ManyToMany( mappedBy = "modules")
    private List<Course> courses;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getProfessorNames() {
        return professorNames;
    }

    public List<String> getProfessorNamesAsList() {

        String temp = this.professorNames;
        List<String> professors;

        String[] result = temp.split(",");

        professors = Arrays.asList(result);

        return professors;
    }


    public void setProfessorNames(String professorNames) {
        this.professorNames = professorNames;
    }

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

    public void removeProfessorName(String professorName){

        String temp = this.professorNames;

        temp = temp.replace(professorName+",", "");

        this.professorNames = temp;
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
