package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.repository.core.Functions;

import java.util.List;

public class UniversityRepository{

    public UniversityRepository(List<University> universities) {
        this.universities = universities;
    }

    private final Functions<University> universityFunctions = new Functions<University>();
    private final List<University> universities;

    public void save(University university) {
        universityFunctions.save(university, universities);
    }

    public University findByName(String name) {
        return universityFunctions.findByName(name, universities);
    }

    public void delete(String name) {
        universityFunctions.delete(name, universities);
    }

    public List<University> getList() {
        return universities;
    }
}
