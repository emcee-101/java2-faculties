package de.fherfurt.faculty.data.classes;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "university")
public class University {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public University() {

    }

    public Long getId() {
        return id;
    }

    private String presidentName;
    private String name;

    @OneToMany
    private List<Faculty> faculties;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public University(String name, String presidentName){
        this.name = name;
        this.presidentName=presidentName;
    }

    public String getPresidentName() {
        return presidentName;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
}
