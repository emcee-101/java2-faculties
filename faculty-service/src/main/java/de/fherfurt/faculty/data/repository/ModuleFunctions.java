package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Module;

import java.util.Collection;
import java.util.Optional;

public class ModuleFunctions {

    /*
     *
     * filters an inputModuleList with a specified parameter and returns the names of fitting modules in an outputModuleList
     *
     * @param courseName name of the course in which the module takes place
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


    /*
     *
     * filters an inputModuleList with specified parameters and returns the names of fitting modules in an outputModuleList
     *
     * @param courseName name of the course in which the module takes place
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

    public void updateDescriptionDocument(String urlDescriptionDocument, String moduleName){
        Collection<Module> modules = DaoHolder.getInstance().getModuleDao().findAllByFilter("name", moduleName);

        Optional<Module> nullableModule = modules.stream().findFirst();

        if(nullableModule.isPresent()) {
            Module module = nullableModule.get();
            if(!module.getUrlDescriptionDocument().equals(urlDescriptionDocument)) {
                module.setUrlDescriptionDocument(urlDescriptionDocument);

                DaoHolder.getInstance().getModuleDao().update(module);
            }
            else{
                System.out.println("Error: Description document could not be updated!");
            }
        }
        else{
            System.out.println("Error: Module could not be found!");
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

    }

        /*
         *
         * deletes a ProfessorName from the Module-ProfessorNames
         *
         * @param professorName professors name to delete
         * @param moduleName module name, from which the professor name will be deleted
         *

        public void removeProfessorFromModule(String professorName, String moduleName) {
            Module module = moduleRepository.findByName(moduleName);
            if (module.getProfessorNames().contains(professorName)) {
                String newProfessorNames = module.getProfessorNames();
                //newProfessorNames.remove(professorName);
                module.setProfessorNames(newProfessorNames);
                moduleRepository.save(module);
            }
        }

        */
}
