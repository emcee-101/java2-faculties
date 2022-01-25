package de.fherfurt.java1service3;

import de.fherfurt.java1service3.enums.ModuleCertificationType;
import de.fherfurt.java1service3.enums.ModuleType;
import java.util.*;

public class Module {


    private String name;
    private int semester;
    private List<Person> professor;
    private ModuleType typeOfModule;
    private String urlDescriptionDocument;
    private ModuleCertificationType typeOfCertification;


    public Module(String name, int semester, List<Person> professor, ModuleType typeOfModule, String urlDescriptionDocument, ModuleCertificationType typeOfCertification){

        this.name = name;
        this.semester = semester;
        this.professor = professor;
        this.typeOfModule = typeOfModule;
        this.urlDescriptionDocument = urlDescriptionDocument;
        this.typeOfCertification = typeOfCertification;

    };


    // Add a Professor to the list of People overseeing this specific Module
    public void addProfessor(Person newProfessor){

        this.professor.add(newProfessor);

    };

    // Remove a Professor from the list of People overseeing this specific Module
    public void removeProfessor(Person oldProfessor){

        this.professor.remove(oldProfessor);

    };

    // Remove a Professor from the list of People overseeing this specific Module
    public void removeProfessor(int oldProfessorPlaceInList){

        this.professor.remove(oldProfessorPlaceInList-1);

    };

    public String getName(){

        return name;

    };

    public int getSemester(){

        return semester;

    };

    public List<Person> getProf(){

        return professor;

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

    public void setProfessor(List<Person> professor){

        this.professor = professor;

    }

    public void setModuleType (ModuleType typeOfModule){

        this.typeOfModule = typeOfModule;

    };

    public void setURLDescriptionDocument(String newURL){

        this.urlDescriptionDocument = newURL;

    };

    public void setModuleCertificationType (ModuleCertificationType typeOfCertification){

        this.typeOfCertification = typeOfCertification;

    };





}
