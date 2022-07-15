package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Module;

import java.util.Collection;

public class ModuleFunctions {

    /**
     *
     * filters an inputModuleList with a specified parameter and returns the names of fitting modules in an outputModuleList
     *
     * @param courseId id of the course in which the module takes place
     *
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
     *
     * filters an inputModuleList with specified parameters and returns the names of fitting modules in an outputModuleList
     *
     * @param courseId id of the course in which the module takes place
     * @param numberOfSemester the semester in which the module takes place
     *
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
     *
     * updates UrlDescriptionDocument of module with the given Id
     *
     * @param urlDescriptionDocument new urlDescriptionDocument
     * @param moduleId id of the module in which the urlDescriptionDocument is to be updated
     *
     */
    public static void updateDescriptionDocument(String urlDescriptionDocument, long moduleId){
        Module module = DaoHolder.getInstance().getModuleDao().findById(moduleId);

        if(!module.getUrlDescriptionDocument().equals(urlDescriptionDocument)) {
            module.setUrlDescriptionDocument(urlDescriptionDocument);

            DaoHolder.getInstance().getModuleDao().update(module);
        }
        else{
            System.out.println("Error: Description document could not be updated!");
        }
    }


    /**
     * attaches a Name of a Professor to the List of Professors included in the Module Data Structure
     * 
     * @param professorName     name to be added
     * @param id                id of Module in DB
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

    };

        /**
         *
         * deletes a ProfessorName from the Module-ProfessorNames
         *
         * @param professorName professors name to delete
         * @param id module id, from which the professor name will be deleted
         *
         */
        public static Module removeProfessorFromModule(String professorName, long id) {

            Module module = DaoHolder.getInstance().getModuleDao().findById(id);

            module.removeProfessorName(professorName);

            return DaoHolder.getInstance().getModuleDao().update(module);

            }
};

