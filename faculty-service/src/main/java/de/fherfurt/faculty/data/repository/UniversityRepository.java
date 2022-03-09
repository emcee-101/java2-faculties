package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.repository.core.Functions;

import java.util.ArrayList;
import java.util.List;

public class UniversityRepository{

    private final Functions<University> universityFunctions = new Functions<University>();
    private final List<University> universities = new ArrayList<University>();

    public void save(University university) {
        universityFunctions.save(university, universities);
    }

    public void findByName(String name) {
        universityFunctions.findByName(name, universities);
    }

    public void delete(String name) {
        universityFunctions.delete(name, universities);
    }

    public List<University> getList() {
        return universities;
    }
}
