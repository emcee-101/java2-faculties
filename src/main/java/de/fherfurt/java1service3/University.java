package de.fherfurt.java1service3;

public class University {

    private String name;
    private String[] faculty;
    private String president;

    public University(String name, String[] faculty, String president){
        this.name=name;
        this.faculty=faculty;
        this.president=president;
    }

    public String getName() {
        return name;
    }

    public String[] getFaculty() {
        return faculty;
    }

    public String getPresident() {
        return president;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(String[] faculty) {
        this.faculty = faculty;
    }

    public void setPresident(String president) {
        this.president = president;
    }
}
