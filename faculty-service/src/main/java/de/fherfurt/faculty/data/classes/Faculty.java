package de.fherfurt.faculty.data.classes;
import de.fherfurt.faculty.data.classes.core.Basic;

import java.util.*;

public class Faculty extends Basic {

    public Faculty(String deanName, String name, String universityName) {
        super(name);
        this.deanName = deanName;
        this.universityName = universityName;
    }

    private String deanName;
    private String universityName;

    public String getDeanName() {
        return deanName;
    }

    public void setDecanName(String decanName) {
        this.deanName = deanName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}