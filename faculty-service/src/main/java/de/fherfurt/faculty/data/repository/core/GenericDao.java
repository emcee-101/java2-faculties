package de.fherfurt.faculty.data.repository.core;

import com.sun.istack.Nullable;
import de.fherfurt.faculty.data.classes.Module;
import de.fherfurt.faculty.data.classes.University;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    public T delete( long id){

        T entity = this.findById(id);
        return this.delete(entity);
    }

    public T delete( T entity) {

        getEntityManager().getTransaction().begin();
        getEntityManager().remove(entity);
        getEntityManager().getTransaction().commit();

        return entity;

    }

    public List<T> delete( List<T> entries){

        getEntityManager().getTransaction().begin();

        for( T entry : entries){

            getEntityManager().remove(entry);

        }

        getEntityManager().getTransaction().commit();

        return entries;

    }

    public void deleteAll()
    {
        getEntityManager().getTransaction().begin();

        Query query = getEntityManager().createQuery(
                "DELETE FROM " + getEntityClass().getCanonicalName());
        
        query.executeUpdate();

        getEntityManager().getTransaction().commit();

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

    public T findOneByFilter(String attributeName, String attributeValue){

        Collection<T> objects = findAllByFilter(attributeName, attributeValue);

        return (T) objects.stream().findFirst().get();
    }

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

        String queryString = "FROM " + getEntityClass().getCanonicalName() + " as x inner join x." + joinTableName + " as y";


        if (!(attribute1Name == null) && !(attribute1Value == null)) {

            queryString = queryString + " WHERE y." + attribute1Name + " = '" + attribute1Value + "'";

        }

        if (!(attribute2Name == null) && !(attribute2Value == null)) {

            queryString = queryString + " AND y." + attribute2Name + " = '" + attribute2Value + "'";

        }

        Query query = getEntityManager().createQuery(queryString);
        Collection<T> resultTList = new ArrayList<T>();
        // since the join HQL, the return of query.getResultList is of type List<Object[]>
        // in Object[] first index is the type T result, in second index is the joined Class type result
        for (final Object[] result : (List<Object[]>) query.getResultList()) {
            resultTList.add((T) result[0]);
        }
        return resultTList;
    }
}
