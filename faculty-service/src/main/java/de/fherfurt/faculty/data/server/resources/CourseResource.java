package de.fherfurt.faculty.data.server.resources;

import de.fherfurt.faculty.data.classes.Course;
import de.fherfurt.faculty.data.repository.DaoHolder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes all endpoints of functionalities of course operations the server provides
 */
public class CourseResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getAllCourses(){

        TestData newTestData = new TestData();
        List<Course> courses = (List<Course>) DaoHolder.getInstance().getCourseDao().findAll();

        if( courses != null )
            return courses;
        else return new ArrayList<Course>();
    }

    @GET
    @Path("/find-by-id/{course-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourseByID( @PathParam("course-id") long courseId){
        TestData newTestData = new TestData();
        Course course = DaoHolder.getInstance().getCourseDao().findById(courseId);

        if( course != null )
            return Response.ok( course ).build();
        else return Response.status( Response.Status.NOT_FOUND ).build();
    }

    @POST
    @Path("/create/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCourse(Course course){
        Course savedCourse = DaoHolder.getInstance().getCourseDao().create(course);

        if( savedCourse != null )
            return Response.ok( savedCourse ).build();
        else
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
    }

    @DELETE
    @Path("/delete-by-id/{course-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCourseById( @PathParam("course-id") long courseId){
        Course Course = DaoHolder.getInstance().getCourseDao().findById(courseId);

        if( Course != null ) {
            DaoHolder.getInstance().getCourseDao().delete(courseId);
            return Response.ok().build();
        }
        else return Response.status( Response.Status.NOT_FOUND ).build();
    }

    @PUT
    @Path("/update/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCourse( Course course){
        Course updatedCourse = DaoHolder.getInstance().getCourseDao().update(course);

        if( updatedCourse != null )
            return Response.ok( updatedCourse ).build();
        else
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
    }
}