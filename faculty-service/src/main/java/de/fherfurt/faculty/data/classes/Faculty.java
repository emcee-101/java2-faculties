package de.fherfurt.faculty.data.classes;
import de.fherfurt.faculty.data.classes.core.Basic;

import java.util.*;

public class Faculty extends Basic {

    public Faculty(String decanName, String name, String universityName) {
        super(name);
        this.decanName = decanName;
        this.universityName = universityName;
    }

    private String decanName;
    private String universityName;

    public String getDecanName() {
        return decanName;
    }

    public void setDecanName(String decanName) {
        this.decanName = decanName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}