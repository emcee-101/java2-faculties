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
        return DaoHolder.getInstance().getModuleDao().findAllByFilter("courseId", courseId);
    }


    /*
     *
     * filters an inputModuleList with specified parameters and returns the names of fitting modules in an outputModuleList
     *
     * @param courseName name of the course in which the module takes place
     * @param numberOfSemester the semester in which the module takes place
     *
     */
    public List<String> filterModulesBySemesterAndCourse (String courseName, int numberOfSemester) {
        List<Module> inputModuleList;
        inputModuleList = moduleRepository.getList();
        List<String> outputModuleList = new ArrayList<String>();

        for (Module anyModule : inputModuleList) {
            if ((courseName.equals(anyModule.getCourseName())) && (numberOfSemester == anyModule.getSemester())) {
                outputModuleList.add(anyModule.getName());
            }
        }
        return outputModuleList;
    }


        /*
         *
         * updating the DescriptionDocument
         *
         * @param urlDescriptionDocument Document to update
         * @param moduleName module name, in which the Document will be saved
         *
         */
        public void updateDescriptionDocument(String urlDescriptionDocument, String moduleName){
            Module module = moduleRepository.findByName(moduleName);
            if (!module.getUrlDescriptionDocument().equals(urlDescriptionDocument)) {
                module.setUrlDescriptionDocument(urlDescriptionDocument);
                moduleRepository.save(module);
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
            Module module = moduleRepository.findByName(moduleName);
            if (!module.getProfessorNames().contains(professorName)) {
                String newProfessorNames = module.getProfessorNames();
                //newProfessorNames.add(professorName);
                module.setProfessorNames(newProfessorNames);
                moduleRepository.save(module);
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
