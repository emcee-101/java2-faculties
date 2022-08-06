package de.fherfurt.faculty.data.server.resources;

import de.fherfurt.faculty.data.classes.Faculty;
import de.fherfurt.faculty.data.repository.DaoHolder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes all endpoints of functionalities of faculty operations the server provides
 */
public class FacultyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Faculty> getAllFaculties() {
        List<Faculty> faculties = (List<Faculty>) DaoHolder.getInstance().getFacultyDao().findAll();

        if (faculties != null)
            return faculties;
        else return new ArrayList<Faculty>();
    }

    @GET
    @Path("/find-by-id/{faculty-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFacultiesByID(@PathParam("faculty-id") long facultyId) {
        Faculty faculty = DaoHolder.getInstance().getFacultyDao().findById(facultyId);

        if (faculty != null)
            return Response.ok(faculty).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/create/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFaculty(Faculty faculty) {
        Faculty savedFaculty = DaoHolder.getInstance().getFacultyDao().create(faculty);

        if (savedFaculty != null)
            return Response.ok(savedFaculty).build();
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Path("/delete-by-id/{faculty-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFacultyById(@PathParam("faculty-id") long facultyId){
        TestData newTestData = new TestData();

        Faculty deletedFaculty = DaoHolder.getInstance().getFacultyDao().delete(facultyId);

        if( deletedFaculty != null )
            return Response.ok( deletedFaculty ).build();
        else
            return Response.status( Response.Status.NOT_FOUND ).build();
    }

    @PUT
    @Path("/update/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFaculty(Faculty faculty) {
        Faculty updatedFaculty = DaoHolder.getInstance().getFacultyDao().update(faculty);

        if (updatedFaculty != null)
            return Response.ok(updatedFaculty).build();
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/find-dean-by-faculty/{faculty-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findDeanByFaculty(@PathParam( "faculty-id" ) long facultyId) {
        Faculty faculty = DaoHolder.getInstance().getFacultyDao().findById(facultyId);

        if (faculty != null)
            return Response.ok(faculty.getDeanName()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}