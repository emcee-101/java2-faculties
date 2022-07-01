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

    public String outputDeanByFaculty (long facultyId) {
       Faculty faculty = DaoHolder.getInstance().getFacultyDao().findById(facultyId);
       return faculty.getDeanName();


    }

}
