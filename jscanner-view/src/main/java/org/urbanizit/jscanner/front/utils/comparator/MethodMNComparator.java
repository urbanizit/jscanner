package org.urbanizit.jscanner.front.utils.comparator;

import java.util.Comparator;

import org.urbanizit.jscanner.transfert.Method;


public class MethodMNComparator implements Comparator<Method>{

	@Override
	public int compare(Method o1, Method o2) {
		if(o2 == null && o1 == null)return 0;
		if(o1 == null)return -1;
		if(o2 == null)return 1;
		
		String methodName1 = o1.getMethodName();
		String methodName2 = o2.getMethodName();
		if(methodName1 == null && methodName2 == null)return 0;
		if(methodName1 == null)return -1;
		if(methodName2 == null)return 1;
			
		return methodName1.compareToIgnoreCase(methodName2);

	}

}
