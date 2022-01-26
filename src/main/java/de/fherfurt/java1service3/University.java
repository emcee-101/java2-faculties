package de.fherfurt.java1service3;

import java.util.*;

public class University {

    private String name;

    private String presidentName;

    public University(String name, String presidentName){
        this.name=name;
        this.presidentName=presidentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresidentName() {
        return presidentName;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }
}
