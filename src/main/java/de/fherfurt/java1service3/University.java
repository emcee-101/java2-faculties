package de.fherfurt.java1service3;

public class University {

    private String name;

    private Faculty[] faculties;
    private int currentNumberOfFaculties=0;

    private Person president;

    public University(String name, Faculty[] faculties, Person president, int currentNumberOfFaculties){
        this.name=name;
        this.faculties= new Faculty[currentNumberOfFaculties];
        this.president=president;
    }

    public void addFaculty(Faculty faculty){
        this.faculties[currentNumberOfFaculties]=faculty;
        currentNumberOfFaculties++;
    }

    public void addFaculty(String decan, String name, String[] courses){
        this.addFaculty(new Faculty(decan, name, courses));
    }


    public void removeFaculty(Faculty faculty){
        for (int i = 0; i < faculties.length; i++) {
            if (faculties[i] == faculty) {
                int index = i;
                for (int j = index; j < faculties.length - 1; j++) {
                    faculties[j] = faculties[j - 1];
                }
            }
            else{
                System.out.println("Faculty could not be found!");
            }
        }
    }

    public void removeFaculty(String decan, String name, String[] courses){
        this.removeFaculty(new Faculty(decan, name, courses));
    }


    public Faculty searchFaculty(String facultyName){
        for (int i = 0; i < faculties.length; i++) {
            if (faculties[i].getName() == facultyName) {
                return faculties[i];
            }
            else{
                return null;
            }
        } return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty[] getFaculties() {
        return faculties;
    }

    public void setFaculties(Faculty[] faculties) {
        this.faculties = faculties;
    }

    public int getCurrentNumberOfFaculties() {
        return currentNumberOfFaculties;
    }

    public void setCurrentNumberOfFaculties(int currentNumberOfFaculties) {
        this.currentNumberOfFaculties = currentNumberOfFaculties;
    }

    public Person getPresident() {
        return president;
    }

    public void setPresident(Person president) {
        this.president = president;
    }
}
