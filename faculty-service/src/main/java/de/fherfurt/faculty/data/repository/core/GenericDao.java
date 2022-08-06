package de.fherfurt.faculty.data.repository.core;

import com.sun.istack.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Generic Class, in which the Core-Function for all repositories are defined
 */
public class GenericDao <T>{

    private final Class<T> persistentClass;
    private EntityManager entityManager;

    /**
     * Class Constructor
     */
    public GenericDao( Class<T> persistentClass, EntityManager em ) {
        this.persistentClass = persistentClass;
        this.entityManager = em;
    }

    /**
     * Creates an entity
     *
     * @param entity Entity to create
     *
     * @return Created entity
     */
    public T create (T entity)
    {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist( entity );
        getEntityManager().getTransaction().commit();

        return entity;
    }

    /**
     * Creates entities
     *
     * @param entities Entities to create
     *
     * @return Created entities
     */
    public Collection<T> create (Collection<T> entities)
    {
        getEntityManager().getTransaction().begin();

        entities.forEach(entity->{
            getEntityManager().persist( entity );
        });

        getEntityManager().getTransaction().commit();

        return entities;
    }

    /**
     * Updates an entity
     *
     * @param entity Entity to update
     *
     * @return Updated entity
     */
    public T update( T entity) {
        getEntityManager().getTransaction().begin();
        final T savedEntity = getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();

        return savedEntity;
    }

    /**
     * Deletes an entity by ID
     *
     * @param id ID of entity to delete
     *
     * @return Deleted entity
     */
    public T delete( long id){
        T entity = this.findById(id);
        return this.delete(entity);
    }

    /**
     * Deletes an entity
     *
     * @param entity Entity to delete
     *
     * @return Deleted entity
     */
    public T delete( T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(entity);
        getEntityManager().getTransaction().commit();

        return entity;
    }

    /**
     * Deletes entities
     *
     * @param entities Entities to delete
     *
     * @return Deleted entities
     */
    public List<T> delete( List<T> entities){
        getEntityManager().getTransaction().begin();

        for( T entry : entities){
            getEntityManager().remove(entry);
        }

        getEntityManager().getTransaction().commit();

        return entities;
    }

    /**
     * Deletes all entities
     */
    public void deleteAll()
    {
        getEntityManager().getTransaction().begin();

        Query query = getEntityManager().createQuery(
                "DELETE FROM " + getPersistentClass().getCanonicalName());
        
        query.executeUpdate();

        getEntityManager().getTransaction().commit();
    }

    /**
     * Finds an entity by ID
     *
     * @param id ID of entity to find
     *
     * @return Found entity
     */
    public T findById(final long id )
    {
        final T result = getEntityManager().find( persistentClass, id );
        return result;
    }

    /**
     * Returns all entities
     *
     * @return All entities
     */
    public Collection<T> findAll(){
        Query query = getEntityManager().createQuery(
                "FROM " + getPersistentClass().getCanonicalName());
        return (Collection<T>) query.getResultList();
    }

    /**
     * Finds all entities by filter
     *
     * @param attributeName Name of attribute to filter
     * @param attributeValue Value of attribute to filter
     *
     * @return Found entities
     */
    public Collection<T> findAllByFilter(String attributeName, String attributeValue){
        String queryString = "FROM " + getPersistentClass().getCanonicalName() + " WHERE " + attributeName + " = \'" + attributeValue + "\'";
        System.out.println(queryString);

        Query query = getEntityManager().createQuery(queryString);
        return (Collection<T>) query.getResultList();
    }

    /**
     * Finds first entity by filter
     *
     * @param attributeName Name of attribute to filter
     * @param attributeValue Value of attribute to filter
     *
     * @return First found entity
     */
    public T findOneByFilter(String attributeName, String attributeValue){
        Collection<T> objects = findAllByFilter(attributeName, attributeValue);

        return (T) objects.stream().findFirst().get();
    }

    /**
     * Finds all entities by join-filter
     *
     * @param joinTableName Name of the table where the filter will be used on
     * @param attribute1Name Name of first attribute to filter
     * @param attribute1Value Value of first attribute to filter
     * @param attribute2Name Name of second attribute to filter (if second attribute shouldn't be used, set this null)
     * @param attribute2Value Value of second attribute to filter (if second attribute shouldn't be used, set this null)
     *
     * @return Found entities
     */
    public Collection<T> findAllByJoinFilter(
            String joinTableName,
            @Nullable String attribute1Name,
            @Nullable String attribute1Value,
            @Nullable String attribute2Name,
            @Nullable String attribute2Value
    ){
        String queryString = "FROM " + getPersistentClass().getCanonicalName() + " as x inner join x." + joinTableName + " as y";

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

    public Class<T> getPersistentClass(){ return persistentClass; }

    public EntityManager getEntityManager() { return entityManager; }
}
