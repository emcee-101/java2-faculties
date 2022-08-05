package de.fherfurt.faculty.data.server.resources;

import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.repository.DaoHolder;
import de.fherfurt.faculty.data.repository.ModuleFunctions;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class ModuleResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> getAllUniversities(){

        List<Module> modules = (List<Module>) DaoHolder.getInstance().getModuleDao().findAll();

        if( modules != null )
            return modules;
        else return new ArrayList<Module>();

    }

    @GET
    @Path("/find-by-id/{module-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByID( @PathParam("module-id") long moduleId){

        Module module = DaoHolder.getInstance().getModuleDao().findById(moduleId);

        if( module != null )
            return Response.ok( module ).build();
        else return Response.status( Response.Status.NOT_FOUND ).build();

    }

    @POST
    @Path("/create/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByID(Module module){

        Module savedModule = DaoHolder.getInstance().getModuleDao().create(module);

        if( savedModule != null )
            return Response.ok( savedModule ).build();
        else
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
    }

    @DELETE
    @Path("/delete-by-id/{module-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteModuleById( @PathParam("module-id") long moduleId){

        TestData newTestData = new TestData();

        Module deletedModule = DaoHolder.getInstance().getModuleDao().delete(moduleId);

        if( deletedModule != null )
            return Response.ok( deletedModule ).build();
        else return Response.status( Response.Status.NOT_FOUND ).build();

    }

    @PUT
    @Path("/update/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateModule( Module module){

        Module updatedModule = DaoHolder.getInstance().getModuleDao().update(module);

        if( updatedModule != null )
            return Response.ok( updatedModule ).build();
        else
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
    }


    //filterModulesBySemesterAndCourse

    @GET
    @Path("/filter-modules-by-semester-and-course/{course-id}/{semester-number}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> filterModulesBySemesterAndCourse(@PathParam( "course-id" ) String courseID, @PathParam("semester-number") int semesterNumber){

        List<Module> foundModules = filterModulesBySemesterAndCourse(courseID, semesterNumber);

        if( foundModules != null )
            return foundModules;
        else
            return new ArrayList<>();
    }

    @PUT
    @Path("/update-description/{module-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDescriptionDocument(@PathParam( "module-id" ) long moduleId, @QueryParam("description-document") String descriptionDocument) {

        Module updatedModule = ModuleFunctions.updateDescriptionDocument(descriptionDocument, moduleId);

        if (updatedModule != null)
            return Response.ok(updatedModule).build();
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Path("/add-professor-to-module/{module-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessorToModule(@PathParam( "module-id" ) long moduleId, @QueryParam("professor-name") String professorName) {

        Module updatedModule = ModuleFunctions.addProfessorToModule(professorName, moduleId);

        if (updatedModule != null)
            return Response.ok(updatedModule).build();
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

    }



    @PUT
    @Path("/delete-professor-from-module/{module-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeProfessorFromModule(@PathParam( "module-id" ) long moduleId, @QueryParam("professor-name") String professorId){

        Module updatedModule = ModuleFunctions.removeProfessorFromModule(professorId, moduleId);

        if( updatedModule != null )
            return Response.ok( updatedModule ).build();
        else return Response.status( Response.Status.NOT_FOUND ).build();

    }


}
