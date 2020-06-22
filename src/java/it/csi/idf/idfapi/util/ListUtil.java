package it.csi.idf.idfapi.util;

import java.util.Collection;

public class ListUtil {
	
	private ListUtil() {}
	
	public static boolean isEmpty(Collection<?> list) {
		return list == null || list.isEmpty();
	}
	
	public static boolean isNotEmpty(Collection<?> list) {
		return !isEmpty(list);
	}
}
