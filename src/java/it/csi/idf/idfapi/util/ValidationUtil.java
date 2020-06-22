package it.csi.idf.idfapi.util;

import java.util.Arrays;

public class ValidationUtil {
	
	private ValidationUtil() {}
	
	public static boolean isEMail(String mail) {
		String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return mail.matches(pattern);
	}
	
	public static boolean isNumber(String value) {
		String pattern = "\\d+";
		return value.matches(pattern);
	}
	
	public static boolean isBigDecimal(String value) {
		String pattern = "^(((\\d{1,3})(?:,[0-9]{3}){0,4}|(\\d{1,12}))(\\.[0-9]{1,2})?)?$";
		return value.matches(pattern);
	}
	
	public static boolean hasArrayNotNullValue(Object [] paramArray) {
		if (paramArray==null) return false;
		
		for (int i = 0; i < paramArray.length; i++) {
			if (paramArray[i] != null) return true;
		}
		return false;
	}
	
	public static boolean hasNotNullValue(Object [][] arrayOfParamArrays) {
		if (arrayOfParamArrays==null) return false;
		for (int i = 0; i < arrayOfParamArrays.length; i++) {
			if (hasArrayNotNullValue(arrayOfParamArrays[i])) return true;
		}
		return false;
	}
}

