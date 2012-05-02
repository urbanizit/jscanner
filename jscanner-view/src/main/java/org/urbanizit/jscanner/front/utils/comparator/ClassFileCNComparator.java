package org.urbanizit.jscanner.front.utils.comparator;

import java.util.Comparator;

import org.urbanizit.jscanner.transfert.ClassFile;


public class ClassFileCNComparator implements Comparator<ClassFile>{

	@Override
	public int compare(ClassFile o1, ClassFile o2) {
		if(o2 == null && o1 == null)return 0;
		if(o1 == null)return -1;
		if(o2 == null)return 1;
		
		String canonicalName1 = o1.getCanonicalName();
		String canonicalName2 = o2.getCanonicalName();
		if(canonicalName1 == null && canonicalName2 == null)return 0;
		if(canonicalName1 == null)return -1;
		if(canonicalName2 == null)return 1;
		
		return canonicalName1.compareToIgnoreCase(canonicalName2);
	}

}
