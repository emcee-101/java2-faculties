package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Course;
import de.fherfurt.faculty.data.classes.Faculty;
import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.repository.core.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoHolder {

    // Singleton Instance
    private static DaoHolder INSTANCE;

    private EntityManagerFactory factory;
    private EntityManager em;
    private GenericDao<Course> courseDao;
    private GenericDao<Module> moduleDao;
    private GenericDao<Faculty> facultyDao;
    private GenericDao<University> universityDao;


    private DaoHolder(){

        factory = Persistence.createEntityManagerFactory( "faculty_db" );
        em = factory.createEntityManager();

        courseDao = new GenericDao<Course>( Course.class, em );
        moduleDao = new GenericDao<Module>( Module.class, em );
        facultyDao = new GenericDao<Faculty>( Faculty.class, em );
        universityDao = new GenericDao<University>( University.class, em );


    }


    public static DaoHolder getInstance() {

        //Singleton Pattern
        if (INSTANCE == null) {
            INSTANCE = new DaoHolder();
        }

        return INSTANCE;

    }





}



