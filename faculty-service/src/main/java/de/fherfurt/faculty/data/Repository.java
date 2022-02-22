package de.fherfurt.faculty.data;

/*

   Funktionalitäten
   void save (<T> object) -> fügt in Liste ein; verändert, wenn bereits vorhanden (zB IdentifyByName -> universities[1] = "newObject")
  {
       <T> oldObject = findByName(object.name);
       if (oldObject == existent)
           { remove(liste.indexOf (name)); Liste.add(object);}
       else
           add(object);
  }

   <T> findByName(String name)
       - iteriere Variable x
           - prüfe ob element in Liste mit Index x Namensübereinstimmung hat
               - ja : break; variable found auf true
               - nein : x = x++

       if found : return object
       else : return null

   // void delete(String name)
       - FoundObject = findByName
       - Liste.remove(liste.indexOf(FoundObject ));;
*/