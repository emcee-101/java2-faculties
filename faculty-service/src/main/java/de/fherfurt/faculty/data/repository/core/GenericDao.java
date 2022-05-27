package de.fherfurt.faculty.data.repository.core;

import javax.persistence.EntityManager;

public class GenericDao <T>{

    private final Class<T> persistentClass;
    private EntityManager entityManager;


    public GenericDao( Class<T> type, EntityManager em ) {
        this.persistentClass = type;
        this.entityManager = em;
        }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public T findById(final long id )
    {
        final T result = getEntityManager().find( persistentClass, id );
        return result;
        }



}
