package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Faculty;

/**
 * specific functionalities for faculty operations
 */
public class FacultyFunctions {
    /**
     * Finds the name of the head by id of the faculty
     *
     * @param facultyId id of faculty whose leader is to be searched
     *
     * @return Name of the head
     */
    public static String findDeanByFacultyId(long facultyId) {
        Faculty faculty = DaoHolder.getInstance().getFacultyDao().findById(facultyId);
        return faculty.getDeanName();
    }
}
