package org.urbanizit.jscanner.back.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CollectionsUtils {

	/**
	 * Split an collection in subList
	 * @param elements
	 * @param groupSize
	 * @return
	 */
	public static <T> List<List<T>> splitList(Collection<T> elements, int groupSize){
		
		List<List<T>> res = new ArrayList<List<T>>();
		List<T> subList = new ArrayList<T>();

		int i = 1;
		for ( T element : elements) {
			subList.add(element);
			if(i % groupSize == 0){
				res.add(subList);
				subList = new ArrayList<T>();
			}
			i++;
		}
		if(!subList.isEmpty()){
			res.add(subList);
		}		
		return res;
	}	
}
