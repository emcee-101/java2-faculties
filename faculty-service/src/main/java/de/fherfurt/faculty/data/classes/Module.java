package de.fherfurt.faculty.data.classes;


import de.fherfurt.faculty.data.classes.enums.ModuleCertificationType;
import de.fherfurt.faculty.data.classes.enums.ModuleType;

import javax.persistence.*;
import java.util.*;



@Entity
@Table(name = "module")
public class Module {


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
    private String courseName;
    private String name;

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
                  String courseName){

        this.name = name;
        this.semester = semester;
        this.professorNames = professorNames;
        this.typeOfModule = typeOfModule;
        this.urlDescriptionDocument = urlDescriptionDocument;
        this.typeOfCertification = typeOfCertification;
        this.courseName = courseName;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
