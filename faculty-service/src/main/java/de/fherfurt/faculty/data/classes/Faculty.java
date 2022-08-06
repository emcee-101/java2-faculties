package de.fherfurt.faculty.data.classes;

import javax.persistence.*;
import java.util.List;


/**
 *This class represents the data of the different modules including their professors and certification types.
 *The properties are adapted to the special requirements of the class.
 *This is where the set- and get- functions for each property can be found.
 */
@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String deanName;
    private String name;

    @OneToMany( mappedBy = "faculty")
    private List<Course> courses;

    @ManyToOne
    private University university;

    /**
     * Class Constructor
     */
    public Faculty() {}

    /**
     * Class Constructor to generate Faculty with Values
     */
    public Faculty(String deanName,
                   String name,
                   University university) {
        this.name = name;
        this.deanName = deanName;
        this.university=university;
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

    public String getDeanName() {
        return deanName;
    }

    public void setDeanName(String deanName) {
        this.deanName = deanName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}