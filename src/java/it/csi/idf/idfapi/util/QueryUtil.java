package it.csi.idf.idfapi.util;

import java.util.ArrayList;
import java.util.List;

public class QueryUtil {

	
	public static void addConditionForParam(StringBuilder sql, List<Object> params, QueryParam paramValue) {
		if (paramValue!=null && paramValue.getValue()!=null) {
			sql.append(paramValue.fieldName+paramValue.comparisonOperator+"?");
			params.add(paramValue.getValue());
		}
	}
	
	
	public static void addConditionForNotNullParams(StringBuilder sql, List<Object> params, boolean putBrackets, String operator, QueryParam... paramValues) {	
		addConditionForNotNullArrayParams(sql, params, putBrackets, operator, paramValues);	
	}
	
	private static void addConditionForNotNullArrayParams(StringBuilder sql, List<Object> params, boolean putBrackets, String operator, QueryParam [] paramValues) { 
		for (int i = 0; i < paramValues.length; i++) {
			StringBuilder fieldCondition = new StringBuilder(); 
			addConditionForParam(fieldCondition, params, paramValues[i]);
			appendCondition(sql, operator, fieldCondition);
		}
		if (putBrackets && !sql.toString().isEmpty()) { sql.insert(0,'(').append(')');}
	}
	
	public static void addConditionForNotNullParams(StringBuilder sql, List<Object> params, boolean putBrackets, String operator, Object [] values, String fieldName) {
		QueryParam [] queryParamsList = new QueryParam[values.length];
		for (int i = 0; i < values.length; i++) {
			queryParamsList[i] = new QueryParam(values[i], fieldName);
		}
		addConditionForNotNullArrayParams(sql, params, putBrackets, operator, queryParamsList);
	}
	
	public static void addBrackets(StringBuilder sql) {
		if (sql!= null && !sql.toString().isEmpty()) sql.insert(0, '(').append(')');		
	}
	
	public static void appendCondition(StringBuilder sqlCondition, String operator, StringBuilder sqlConditionToAppend) {
		if (sqlConditionToAppend==null || sqlConditionToAppend.toString().trim().isEmpty()) return; // Nothing to append
		if (!sqlCondition.toString().isEmpty()) {
			sqlCondition.append(" "+ operator+ " ");
		}
		sqlCondition.append(sqlConditionToAppend);		
	}
	
	public static void makeWhereQueryStatement(StringBuilder sqlCondition) {
		if (!sqlCondition.toString().isEmpty()) {
			sqlCondition.insert(0, "WHERE");
		}
	}
	
	
}
