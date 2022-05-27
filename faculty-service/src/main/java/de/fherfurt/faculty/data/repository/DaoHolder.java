package de.fherfurt.faculty.data.repository;

import de.fherfurt.faculty.data.classes.Course;
import de.fherfurt.faculty.data.classes.Faculty;
import de.fherfurt.faculty.data.classes.University;
import de.fherfurt.faculty.data.repository.core.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoHolder {

    static EntityManagerFactory factory;
    static EntityManager em;
    static GenericDao<Course> courseDao;
    static GenericDao<Module> moduleDao;
    static GenericDao<Faculty> facultyDao;
    static GenericDao<University> universityDao;


    public static void init(){

        factory = Persistence.createEntityManagerFactory( "faculty_db" );
        em = factory.createEntityManager();

        courseDao = new GenericDao<Course>( Course.class, em );
        moduleDao = new GenericDao<Module>( Module.class, em );
        facultyDao = new GenericDao<Faculty>( Faculty.class, em );
        universityDao = new GenericDao<University>( University.class, em );

    }



}



