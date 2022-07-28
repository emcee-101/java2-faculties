package de.fherfurt.faculty.data.server.resources;

import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.repository.DaoHolder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class ModuleResource {



    //filterModulesBySemesterAndCourse

    @GET
    @Path("/filterModulesBySemesterAndCourse/{courseID}/{semesterNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Module> filterModulesBySemesterAndCourse(@PathParam( "courseID" ) String courseID, @PathParam("semesterNumber") int semesterNumber){

        TestData newTestData = new TestData();

        List<Module> foundModules = filterModulesBySemesterAndCourse(courseID, semesterNumber);

        if( foundModules != null )
            return foundModules;
        else
            return new ArrayList<>();
    }



}
