package de.fherfurt.java1service3;

import de.fherfurt.java1service3.enums.ModuleCertificationType;
import de.fherfurt.java1service3.enums.ModuleType;

public class Module {


    private String name;
    private int semester;
    private String[] professor;
    private ModuleType typeOfModule;
    private String urlDescriptionDocument;
    private ModuleCertificationType typeOfCertification;


    public String getName(){

        return name;

    };

    public int getSemester(){

        return semester;

    };

    public String[] getProf(){

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

    public void setURLDescriptionDocuemnt(String newURL){

        urlDescriptionDocument = newURL;

    };


    public Module(String name, int semester, String[] professor, ModuleType typeOfModule, String urlDescriptionDocument, ModuleCertificationType typeOfCertification){

        this.name = name;
        this.semester = semester;
        this.professor = professor;
        this.typeOfModule = typeOfModule;
        this.urlDescriptionDocument = urlDescriptionDocument;
        this.typeOfCertification = typeOfCertification;

    };



}
