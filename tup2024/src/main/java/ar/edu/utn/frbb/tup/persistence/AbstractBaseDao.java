package ar.edu.utn.frbb.tup.persistence;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBaseDao {
    protected static Map<String, Map<Long, Object>> poorMansDatabase = new HashMap<>(); //Esta es una variable estática de tipo Map que almacena una estructura de datos en memoria para simular una base de datos
    protected abstract String getEntityName(); //Este es un método abstracto que debe ser implementado por las clases concretas que extiendan AbstractBaseDao

    protected Map<Long, Object> getInMemoryDatabase() {  //Este método devuelve el Map que contiene los objetos de la entidad correspondiente a la clase DAO que lo invoca
        if (poorMansDatabase.get(getEntityName()) == null) {
            poorMansDatabase.put(getEntityName(),new HashMap<>());
        }
        return poorMansDatabase.get(getEntityName());
    }
}
