package de.fherfurt.faculty.objects;

public class FacultyProduction implements FacultyClient {
    @Override
    public boolean isFacultynameValid(String name) {
        return true;
    };
}