package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModuleFunctions {

    /*
     *
     * filters an inputModuleList with a specified parameter and returns the names of fitting modules in an outputModuleList
     *
     * @param courseName name of the course in which the module takes place
     *
     */
    public Collection<Module> filterModulesByCourse (String courseId) {
        return DaoHolder.getInstance().getModuleDao().findAllByFilterChris(
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
     * @param numberOfSemester the semAester in which the module takes place
     *
     */
    public Collection<Module> filterModulesBySemesterAndCourse (String courseId, int numberOfSemester) {
        return DaoHolder.getInstance().getModuleDao().findAllByFilterChris(
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

    /*
     *
     * adds a ProfessorName to the Module-ProfessorNames
     *
     * @param professorName professors name to add
     * @param moduleName module name, in which the professor name will be saved
     *
     */
    public void addProfessorToModule(String professorName, String moduleName) {

        Collection<Module> modules = DaoHolder.getInstance()
                .getModuleDao()
                .findAllByFilter("name", "moduleName");

        Optional<Module> nullableModule = modules.stream()
                .findFirst();

        if (nullableModule.isPresent()) {

            Module module = nullableModule.get();

            module.addProfessorName(professorName);

            Module updatedModule = DaoHolder.getInstance()
                    .getModuleDao()
                    .update(module);
        }
        else{
            System.out.println("an error has occured while accessing the Module data :(");
        }

    }

        /*
         *
         * deletes a ProfessorName from the Module-ProfessorNames
         *
         * @param professorName professors name to delete
         * @param moduleName module name, from which the professor name will be deleted
         *
         */
        public void removeProfessorFromModule(String professorName, String moduleName) {
            Module module = moduleRepository.findByName(moduleName);
            if (module.getProfessorNames().contains(professorName)) {
                String newProfessorNames = module.getProfessorNames();
                //newProfessorNames.remove(professorName);
                module.setProfessorNames(newProfessorNames);
                moduleRepository.save(module);
            }
        }


}
