package org.urbanizit.jscanner.front.utils.comparator;

import java.util.Comparator;

import org.urbanizit.jscanner.transfert.Method;


public class MethodeCNComparator implements Comparator<Method>{

	@Override
	public int compare(Method o1, Method o2) {
		Integer res = nullComparator(o1, o2);
		if(res != null){return res;	}
		
		String canonicalName1 = o1.getClassName();
		String canonicalName2 = o2.getClassName();
		
		res = nullComparator(canonicalName1, canonicalName2);
		if(res != null){return res;	}
		
		return new StringComparator().compare(canonicalName1, canonicalName2);
	}

	private Integer nullComparator(Object o1, Object o2){
		if(o2 == null && o1 == null)return 0;
		if(o1 == null)return -1;
		if(o2 == null)return 1;
		return null;
	}
}
