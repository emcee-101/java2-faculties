package de.fherfurt.faculty.data.server;

import de.fherfurt.faculty.data.server.resources.TestData;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.LoggerFactory;

import java.net.URI;
import org.slf4j.Logger;

import static de.fherfurt.faculty.data.server.resources.TestData.getTestData;


public class Backend {

    public static final String Base_URL = "http://localhost:10000/";

    public static final Boolean useTestData = true;

    public static TestData testData(){
        return getTestData();
    }


    public static Server startServer() {

        // Register REST Endpoint Classes
            // CHANGE MODULE.CLASS later - its supposed to be a Resource-Class!!
        final ResourceConfig config = new ResourceConfig(REST_configuration.class);

        // Start Jetty Server
        final Server server = JettyHttpContainerFactory.createServer(URI.create(Base_URL), config);

        return server;
    }

    public static void main(String[] args){
        Logger LOG = LoggerFactory.getLogger( Backend.class );

        // CREATE TEST DATA
        if(useTestData) testData();

        try {
            final Server server = startServer();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try{
                    server.stop();
                }
                catch (Exception e) {LOG.error(null, e);}
            }));
            LOG.info("Application started. -- Stop the application using CTRL+C\n");

            Thread.currentThread().join();
        }
        catch (InterruptedException ex) { LOG.error(null, ex); }
    };

}
