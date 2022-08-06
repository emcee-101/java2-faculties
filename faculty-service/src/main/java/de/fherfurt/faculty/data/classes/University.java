package de.fherfurt.faculty.data.classes;

import javax.persistence.*;
import java.util.List;


/**
 *This class represents the data of the different universities including their faculty and president.
 *The properties are adapted to the special requirements of the class.
 *This is where the set- and get- functions for each property can be found.
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
