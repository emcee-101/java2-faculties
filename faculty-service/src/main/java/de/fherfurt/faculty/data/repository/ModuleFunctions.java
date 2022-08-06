package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Module;

import java.util.Collection;

/**
 * specific functionalities for module operations
 */
public class ModuleFunctions {
    /**
     * Filters an inputModuleList with a specified parameter
     *
     * @param courseId id of the course in which the module takes place
     *
     * @return Names of fitting modules in an outputModuleList
     */
    public Collection<Module> filterModulesByCourse (String courseId) {
        return DaoHolder.getInstance().getModuleDao().findAllByJoinFilter(
                "module",
                "courseId",
                courseId,
                null,
                null
        );
    }

    /**
     * Filters an inputModuleList with specified parameters
     *
     * @param courseId id of the course in which the module takes place
     * @param numberOfSemester the semester in which the module takes place
     *
     * @return  Names of fitting modules in an outputModuleList
     */
    public Collection<Module> filterModulesBySemesterAndCourse (String courseId, int numberOfSemester) {
        return DaoHolder.getInstance().getModuleDao().findAllByJoinFilter(
                "module",
                "courseId",
                courseId,
                "numberOfSemester",
                Integer.toString(numberOfSemester)
        );
    }

    /**
     * Updates UrlDescriptionDocument of module with the given Id
     *
     * @param urlDescriptionDocument new urlDescriptionDocument
     * @param moduleId id of the module in which the urlDescriptionDocument is to be updated
     *
     * @return Module in which the urlDescriptionDocument was updated or null if update failed
     */
    public static Module updateDescriptionDocument(String urlDescriptionDocument, long moduleId){
        Module module = DaoHolder.getInstance().getModuleDao().findById(moduleId);

        if(!module.getUrlDescriptionDocument().equals(urlDescriptionDocument)) {
            module.setUrlDescriptionDocument(urlDescriptionDocument);

            return DaoHolder.getInstance().getModuleDao().update(module);
        }
        else{
            System.out.println("Error: Description document could not be updated!");
            return null;
        }
    }

    /**
     * attaches a Name of a Professor to the List of Professors included in the Module Data Structure
     * 
     * @param professorName     name to be added
     * @param id                id of Module in DB
     *
     * @return                  the edited Module
     */
    public static Module addProfessorToModule(String professorName, long id) {
        Module module = DaoHolder.getInstance()
                                 .getModuleDao()
                                 .findById(id);

        module.addProfessorName(professorName);

        return DaoHolder.getInstance()
                        .getModuleDao()
                        .update(module);
    }

    /**
     * deletes a ProfessorName from the Module-ProfessorNames
     *
     * @param professorName professors name to delete
     * @param id module id, from which the professor name will be deleted
     *
     * @return Module in which the Professor was removed
     */
    public static Module removeProfessorFromModule(String professorName, long id) {
        Module module = DaoHolder.getInstance().getModuleDao().findById(id);

        module.removeProfessorName(professorName);

        return DaoHolder.getInstance().getModuleDao().update(module);
    }
}

