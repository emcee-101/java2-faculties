package de.fherfurt.faculty.data.classes;

import de.fherfurt.faculty.data.classes.core.Basic;

import java.util.*;

public class University extends Basic {

    private String presidentName;

    public University(String name, String presidentName){
        super(name);
        this.presidentName=presidentName;
    }

    public String getPresidentName() {
        return presidentName;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }
}
