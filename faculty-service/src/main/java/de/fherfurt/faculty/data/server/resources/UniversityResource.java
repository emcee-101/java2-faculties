package de.fherfurt.faculty.data.server.resources;

import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.repository.DaoHolder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UniversityResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<University> getAllUniversities(){

        TestData newTestData = new TestData();

        List<University> universities = (List<University>) DaoHolder.getInstance().getUniversityDao().findAll();

        if( universities != null )
            return universities;
        else return new ArrayList<University>();

    }

    @GET
    @Path("/findById/{universityId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUniversitiesByID( @PathParam("universityId") long  universityId){

        TestData newTestData = new TestData();

        University university = DaoHolder.getInstance().getUniversityDao().findById(universityId);

        if( university != null )
            return Response.ok( university ).build();
        else return Response.status( Response.Status.NOT_FOUND ).build();

    }

    @POST
    @Path("/create/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUniversitiesByID( University uni){

        University savedUni = DaoHolder.getInstance().getUniversityDao().create(uni);

        if( savedUni != null )
            return Response.ok( savedUni ).build();
        else
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
    }

    @PUT
    @Path("/update/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUniversity( University uni){

        TestData newTestData = new TestData();

        University savedUni = DaoHolder.getInstance().getUniversityDao().update(uni);

        if( savedUni != null )
            return Response.ok( savedUni ).build();
        else
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
    }

}
