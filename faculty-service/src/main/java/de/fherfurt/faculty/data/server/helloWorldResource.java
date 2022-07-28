package de.fherfurt.faculty.data.server;
import de.fherfurt.faculty.data.classes.University;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class helloWorldResource
{
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Response getMessage()
        {

            University uniTest = new University("Leipzig", "Jochen");

            if( uniTest != null )
                return Response.ok( uniTest ).build();
            else
                return Response.status( Response.Status.NOT_FOUND ).build();

        }
}