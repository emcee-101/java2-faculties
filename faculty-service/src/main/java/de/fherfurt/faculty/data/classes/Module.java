package de.fherfurt.faculty.data.classes;

import de.fherfurt.faculty.data.classes.core.Basic;
import de.fherfurt.faculty.data.classes.enums.ModuleCertificationType;
import de.fherfurt.faculty.data.classes.enums.ModuleType;
import java.util.*;

public class Module extends Basic {
    private int semester;
    private List<String> professorNames;
    private ModuleType typeOfModule;
    private String urlDescriptionDocument;
    private ModuleCertificationType typeOfCertification;
    private String courseName;

    public Module(String name,
                  int semester,
                  List<String> professorNames,
                  ModuleType typeOfModule,
                  String urlDescriptionDocument,
                  ModuleCertificationType typeOfCertification,
                  String courseName){

        super(name);
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

    public List<String> getProfessorNames() {
        return professorNames;
    }

    public void setProfessorNames(List<String> professorNames) {
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
