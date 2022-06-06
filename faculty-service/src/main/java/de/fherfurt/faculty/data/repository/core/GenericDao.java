package de.fherfurt.faculty.data.repository.core;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

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

    public Class<T> getEntityClass(){

        return persistentClass;

    }

    public T findById(final long id )
    {
        final T result = getEntityManager().find( persistentClass, id );
        return result;
        }

    public T create (T entity)
    {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist( entity );
        getEntityManager().getTransaction().commit();;

        return entity;
    }

    public void delete( long id){

        T entity = this.findById(id);
        this.delete(entity);
    }

    public void delete( T entity) {

        getEntityManager().getTransaction().begin();
        getEntityManager().remove(entity);
        getEntityManager().getTransaction().commit();

    }

    public void delete( List<T> entries){

        getEntityManager().getTransaction().begin();

        for( T entry : entries){

            getEntityManager().remove(entry);

        }

        getEntityManager().getTransaction().commit();

    }

    public Collection<T> findAll(){

        Query query = getEntityManager().createQuery(
                "SELECT * FROM " + getEntityClass().getCanonicalName());
        return (Collection<T>) query.getResultList();

    }


    public T update( T entity) {

        getEntityManager().getTransaction().begin();
        final T savedEntity = getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();

        return savedEntity;

    }

}
