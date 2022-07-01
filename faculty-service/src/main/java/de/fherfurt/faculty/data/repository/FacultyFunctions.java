package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Faculty;

public class FacultyFunctions {

    /*
     *
     * returns the Name of the Head of this faculty
     *
     * @param facultyName name of faculty whose leader is to be searched
     *
     */
    public String outputDeanByFaculty(String facultyName){

        Faculty desiredFaculty = facultyRepository.findByName(facultyName);

        String dean;

        if (desiredFaculty == null){
            dean = "Faculty not found";
        }
        else{
            dean = desiredFaculty.getDeanName();
        }

        return dean;
    }

}
