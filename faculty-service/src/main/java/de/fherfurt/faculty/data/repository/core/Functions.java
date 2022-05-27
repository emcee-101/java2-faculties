package de.fherfurt.faculty.data.repository.core;

import de.fherfurt.faculty.data.classes.core.Basic;

import java.util.*;

public class Functions<T> {
    /*
    * saves the entity in the list
    *
    * @param entity object to save
    * @param list the unique list which contains all items
    *
     */
    public void save(T entity, List<T> list) {
        T foundItem = findByName(entity.getName(), list);
        if (foundItem == null) {
            // new Entry
            list.add(entity);
        } else {
            // update Entry
            list.add(list.indexOf(foundItem), entity);
        }
    }

    /*
     * finds an entity by name
     *
     * @param name name-property to identify the object
     * @param list the unique list which contains all items
     *
     * @return the object found by name or null, if no object was found
     */
    public T findByName(String name, List<T> list) {
        return list.stream()
                .filter(ent -> ent.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /*
     * deletes an entity by name
     *
     * @param name name-property to identify the object
     * @param list the unique list which contains all items
     *
     */
    public void delete(String name, List<T> list) {
        T foundItem = findByName(name, list);
        if (foundItem != null) {
            // delete Object
            list.remove(foundItem);
        }
    }
}