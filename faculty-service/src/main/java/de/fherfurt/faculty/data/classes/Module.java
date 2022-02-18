package de.fherfurt.java1service3.data.classes;

import de.fherfurt.java1service3.data.classes.enums.ModuleCertificationType;
import de.fherfurt.java1service3.data.classes.enums.ModuleType;
import java.util.*;

public class Module {
    private String name;
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

        this.name = name;
        this.semester = semester;
        this.professorNames = professorNames;
        this.typeOfModule = typeOfModule;
        this.urlDescriptionDocument = urlDescriptionDocument;
        this.typeOfCertification = typeOfCertification;
        this.courseName = courseName;

    };

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getName(){

        return name;

    };

    public int getSemester(){

        return semester;

    };

    public ModuleType getType(){

        return typeOfModule;

    };

    public String getURLDescriptionDocument(){

        return urlDescriptionDocument;

    };

    public ModuleCertificationType getTypeOfCertification(){

        return typeOfCertification;

    };


    public void setName(String name){

        this.name = name;

    };

    public void setSemester(int semester){

        this.semester = semester;

    };

    public void setModuleType (ModuleType typeOfModule){

        this.typeOfModule = typeOfModule;

    };

    public void setURLDescriptionDocument(String newURL){

        this.urlDescriptionDocument = newURL;

    };

    public void setModuleCertificationType (ModuleCertificationType typeOfCertification){

        this.typeOfCertification = typeOfCertification;

    };

    public List<String> getProfessorNames() {
        return professorNames;
    }

    public void setProfessorNames(List<String> professorNames) {
        this.professorNames = professorNames;
    }
}
