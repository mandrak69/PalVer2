package it.csi.idf.idfapi.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;

public class Q extends SpringSupportedResource {
	static ObjectMapper objectMapper;
	
	
	public static String createQueryListOfColumnsForInsert(String[] columnNames) {

		StringBuilder sql = new StringBuilder();
		String separator = "";
		for (int i = 0; i < columnNames.length; i++) {
			sql.append(separator.concat(columnNames[i]));
			if (i == 0) {
				separator = ",";
			}
		}

		return sql.toString();
	}

	public static String createQueryListOfParamsForInsert(String[] columnNames) {

		StringBuilder sql = new StringBuilder();
		String separator = "";
		for (int i = 0; i < columnNames.length; i++) {
			sql.append(separator.concat(" ?"));
			if (i == 0) {
				separator = ",";
			}
		}

		return sql.toString();
	}

	public static String createQueryOfColumnsForUpdate(String[] columnNames) {

		StringBuilder sql = new StringBuilder();
		String separator = ",";
		for (int i = 0; i < columnNames.length; i++) {
			if (i == columnNames.length - 1) {
				separator = " ";
			}
			sql.append(columnNames[i]).append("=?" + separator);
		}

		return sql.toString();
	}

	public static String createInsertQueryString(String tableName, String[] columnNames) {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("INSERT INTO  %s(", tableName))
				.append(Q.createQueryListOfColumnsForInsert(columnNames)).append(")")
				.append(String.format("VALUES( %s )", Q.createQueryListOfParamsForInsert(columnNames)));

		return sql.toString();
	}

	public static String createUpdateQueryString(String tableName, String[] columnNames, String pkColumnName) {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s SET ", tableName))
				.append(Q.createQueryOfColumnsForUpdate(columnNames)).append(getPkWhereClause(pkColumnName));

		return sql.toString();
	}

	public static String createCountByPkQuery(String tableName, String pkColumnName) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM " + tableName).append(String.format(" WHERE %s=?", pkColumnName));

		return sql.toString();
	}

	public static String getPkWhereClause(String pkColumnName) {
		return String.format(" WHERE %s=?", pkColumnName);
	}

	public static StringBuilder columnForTable(String tableName) {
		StringBuilder strb = new StringBuilder();
		Field[] fields;
		try {
			fields = Class.forName(tableName).getDeclaredFields();
			String zarez = "";
			for (Field f : fields) {
				strb.append(f.getName()).append(zarez);
				zarez = ",";
			}

		} catch (SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return strb;
	}

	
	
	
	public static <T> StringBuilder insertObject(String tableName,Object obj)
			throws IllegalArgumentException, IllegalAccessException {
		// DM moze da se uvede static final tableName u klasi objekta da ukazuje na tabelu  obj.tableName=*****
		// onda bi se svelo na slanje objekta
		
		
		
		String json;
		
		
		Class<? extends Object> forClass = obj.getClass();
		
		StringBuilder nameStr = new StringBuilder("INSERT INTO ").append(tableName).append(" ( ");
		StringBuilder valStr = new StringBuilder();
		
		Field[] fields;
		try {
			fields = forClass.getDeclaredFields();
			Annotation[] annt = forClass.getDeclaredAnnotations();
			String zarez = "";
			String navod = "'";
			for (Field f : fields) {

				boolean accessible = f.isAccessible();

				f.setAccessible(true);
				
				
				
				 Annotation[] column = f.getAnnotations();
				   if (column != null) {
				       System.out.println(column[0]);
				       }
				   String ime="";
				   if(column[0] instanceof JsonProperty){
		                JsonProperty property = (JsonProperty) column[0];
		                 ime = property.value();
		            }
				nameStr.append(zarez).append(ime);
				if (f.get(obj) == (null)) {
					valStr.append(navod).append("null");
				} else {
					Object fff = f.get(obj);
					valStr.append(navod).append(fff.toString());
				}

				zarez = " , ";
				navod="' , '";
				f.setAccessible(accessible);
			}
			valStr.append(" ' ");
			
			System.out.println( nameStr.toString() + " ) VALUES (" + valStr.toString()+" );");

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nameStr.append(" ) VALUES (").append(valStr).append(");");
	}
	public static <T> StringBuilder updateObject(String tableName,Object obj)
			throws IllegalArgumentException, IllegalAccessException {
		// DM moze da se uvede static final tableName u klasi objekta da ukazuje na tabelu  obj.tableName=*****
		// onda bi se svelo na slanje objekta
		
		Class<? extends Object> forClass = obj.getClass();
		
		StringBuilder nameStr = new StringBuilder("UPDATE ").append(tableName).append(" SET ( ");
		StringBuilder valStr = new StringBuilder();
		
		Field[] fields;
		try {
			fields = forClass.getDeclaredFields();
			String zarez = "";
			String navod = "'";
			for (Field f : fields) {

				boolean accessible = f.isAccessible();

				f.setAccessible(true);

				nameStr.append(zarez).append(f.getName()).append("=");
				if (f.get(obj) == (null)) {
					nameStr.append(navod).append("null");
				} else {
					Object fff = f.get(obj);
					nameStr.append(navod).append(fff.toString());
				}

				zarez = " , ";
				navod="' , '";
				f.setAccessible(accessible);
			}
			nameStr.append(" ' ");
			
			System.out.println( nameStr.toString() + " ) WHERE (" + valStr.toString()+" )");

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nameStr.append(" ) VALUES (").append(valStr);
	}


	  
}

