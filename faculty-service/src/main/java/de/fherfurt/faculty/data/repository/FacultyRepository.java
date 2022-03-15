package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Faculty;
import de.fherfurt.faculty.data.repository.core.Functions;

import java.util.ArrayList;
import java.util.List;

public class FacultyRepository {

    public FacultyRepository(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    private final Functions<Faculty> facultyFunctions = new Functions<Faculty>();
    private final List<Faculty> faculties;

    public void save(Faculty faculty) {
        facultyFunctions.save(faculty, faculties);
    }

    public Faculty findByName(String name) {
        return facultyFunctions.findByName(name, faculties);
    }

    public void delete(String name) {
        facultyFunctions.delete(name, faculties);
    }

    public List<Faculty> getList() {
        return faculties;
    }
}
