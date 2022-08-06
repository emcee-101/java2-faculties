package de.fherfurt.faculty.data.classes;

import javax.persistence.*;
import java.util.List;

/**
 * Entity-Class for University
 */
@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String presidentName;

    @OneToMany( mappedBy = "university")
    private List<Faculty> faculties;

    /**
     * Class Constructor
     */
    public University() {}

    /**
     * Class Constructor to generate University with Values
     */
    public University(String name, String presidentName){
        this.name = name;
        this.presidentName=presidentName;
    }

    public Long getId() {
        return id;
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

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
}
