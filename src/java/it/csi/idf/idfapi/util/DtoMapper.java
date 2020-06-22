package it.csi.idf.idfapi.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class DtoMapper<T> {

    private Class clazz;
    private Map<String, Field> fields = new HashMap<>();
    Map<String, String> errors = new HashMap<>();

    public  DtoMapper(Class clazz) {
        this.clazz = clazz;

        List<Field> fieldList = Arrays.asList(clazz.getDeclaredFields());
        for (Field field : fieldList) {
        	Annotation[] column = field.getAnnotations();
        	 
			   String ime="";
			   if(column[0] instanceof JsonProperty){
	                JsonProperty property = (JsonProperty) column[0];
	                 ime = property.value();
	                 field.setAccessible(true);
	                 //  json_name, filed
	                 fields.put(ime, field);
	            }
        	
        	
        }
    }

    public T map(Map<String, Object> row) throws SQLException {
        try {
            T dto = (T) clazz.getConstructor().newInstance();
            for (Map.Entry<String, Object> entity : row.entrySet()) {
                if (entity.getValue() == null) {
                    continue;  // Don't set DBNULL
                }
                String column = entity.getKey();
                Field field = fields.get(column);
                if (field != null) {
                    field.set(dto, convertInstanceOfObject(entity.getValue()));
                }
            }
            return dto;
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new SQLException("Problem with data Mapping. See logs.");
        }
    }

    public List<T> map(List<Map<String, Object>> rows) throws SQLException {
        List<T> list = new LinkedList<>();

        for (Map<String, Object> row : rows) {
            list.add(map(row));
        }

        return list;
    }

    private T convertInstanceOfObject(Object o) {
        try {
            return (T) o;
        } catch (ClassCastException e) {
            return null;
        }
    }
}
