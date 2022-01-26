package de.fherfurt.java1service3;
import java.util.*;

public class Faculty {

    public Faculty(String decanName, String name, String universityName) {
        this.decanName = decanName;
        this.name = name;
        this.universityName = universityName;
    }

    private String decanName;
    private String name;
    private String universityName;

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public static void main(String[] args) {
        System.out.println("hello world!");
    }

    public String getDecanName() {
        return decanName;
    }

    public void setDecanName(String decanName) {
        this.decanName = decanName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}