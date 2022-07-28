package de.fherfurt.faculty.data.server;

import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.server.resources.CourseResource;
import de.fherfurt.faculty.data.server.resources.FacultyResource;
import de.fherfurt.faculty.data.server.resources.ModuleResource;
import de.fherfurt.faculty.data.server.resources.UniversityResource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1")
public class REST_configuration {

    @GET
    @Produces( MediaType.TEXT_PLAIN )
    public String getMessage()
    {
        return "hello";
    }

    @Path("/university")
    public UniversityResource getUniversityResource(){
        return new UniversityResource();
    }

    @Path("/module")
    public ModuleResource getModuleResource(){
        return new ModuleResource();
    }

    @Path("/faculty")
    public FacultyResource getFacultyResource(){
        return new FacultyResource();
    }

    @Path("/course")
    public CourseResource getCourseResource(){
        return new CourseResource();
    }
}
