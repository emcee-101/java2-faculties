package de.fherfurt.faculty.data.repository.core;

import com.sun.istack.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.MessageFormat;
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

    public T create (T entity)
    {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist( entity );
        getEntityManager().getTransaction().commit();

        return entity;
    }

    public Collection<T> create (Collection<T> entities)
    {
        getEntityManager().getTransaction().begin();

        entities.forEach(entity->{
            getEntityManager().persist( entity );
        });

        getEntityManager().getTransaction().commit();

        return entities;
    }

    public T update( T entity) {

        getEntityManager().getTransaction().begin();
        final T savedEntity = getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();

        return savedEntity;

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

    public void deleteAll()
    {
        Query query = getEntityManager().createQuery(
                "DELETE FROM " + getEntityClass().getCanonicalName());
    }

    public T findById(final long id )
    {
        final T result = getEntityManager().find( persistentClass, id );
        return result;
    }

    public Collection<T> findAll(){

        Query query = getEntityManager().createQuery(
                "FROM " + getEntityClass().getCanonicalName());
        return (Collection<T>) query.getResultList();

    }

    //select module0_.id as id1_3_, module0_.name as name2_3_, module0_.professorNames as professo3_3_, module0_.semester as semester4_3_, module0_.typeOfCertification as typeofce5_3_, module0_.typeOfModule as typeofmo6_3_, module0_.urlDescriptionDocument as urldescr7_3_ from module module0_ where module0_.name=java1 [42122-212]

    public Collection<T> findAllByFilter(String attributeName, String attributeValue){
        String queryString = "FROM " + getEntityClass().getCanonicalName() + " WHERE " + attributeName + " = \'" + attributeValue + "\'";
        System.out.println(queryString);

        Query query = getEntityManager().createQuery(queryString);
        return (Collection<T>) query.getResultList();
    }

    public Collection<T> findAllByJoinFilter(
            String joinTableName,
            @Nullable String attribute1Name,
            @Nullable String attribute1Value,
            @Nullable String attribute2Name,
            @Nullable String attribute2Value
    ){
        String queryString = MessageFormat.format(
                "FROM {0} inner join {0}.{1}",
                getEntityClass().getCanonicalName(),
                joinTableName
        );

        if (!(attribute1Name == null) && !(attribute1Value == null)) {
            queryString = MessageFormat.format(
                    "{0} WHERE {1} = \'{2}\'",
                    queryString,
                    attribute1Name,
                    attribute1Value
            );
        }

        if (!(attribute2Name == null) && !(attribute2Value == null)) {
            queryString = MessageFormat.format(
                    "{0} AND {1} = \'{2}\'",
                    queryString,
                    attribute2Name,
                    attribute2Value
            );
        }

        Query query = getEntityManager().createQuery(queryString);
        return (Collection<T>) query.getResultList();
    }

}
