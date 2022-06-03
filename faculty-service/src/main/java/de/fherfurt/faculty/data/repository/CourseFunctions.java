package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Module;

import java.util.ArrayList;
import java.util.List;

public class CourseFunctions {

    /*
     *
     * filters an inputModuleList with a specified parameter and returns the names of fitting modules in an outputModuleList
     *
     * @param courseName name of the course in which the module takes place
     *
     */
    public List<String> filterModulesByCourse (String courseName) {
        List<Module> inputModuleList;
        inputModuleList = moduleRepository.getList();
        List<String> outputModuleList = new ArrayList<String>();

        for (Module anyModule : inputModuleList) {
            if (courseName.equals(anyModule.getCourseName())) {
                outputModuleList.add(anyModule.getName());
            }
        }
        return outputModuleList;
    }
    
    
}
