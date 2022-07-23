package de.fherfurt.faculty.data.server;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello")
public class helloWorldResource
{
    @GET
    @Produces( MediaType.TEXT_PLAIN )
    public String getMessage()
        {
            return "Hello World";
        }
}