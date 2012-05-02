package org.urbanizit.jscanner.back.persistence.criteria;

import java.io.Serializable;
import java.util.Collection;

public class ClassFileBoCriteria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Collection<Long> consumedClassesIds;
	private Collection<String> consumedClassesNames;

}
