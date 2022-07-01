package de.fherfurt.faculty.data.classes;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Faculty() {

    }


    public Long getId() {
        return id;
    }

    public Faculty(String deanName, String name, List<Course> courses) {
        this.name = name;
        this.deanName = deanName;
        this.courses=courses;
    }

    private String deanName;
    private String name;

    @OneToMany
    private List<Course> courses;

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