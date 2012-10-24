package org.urbanizit.jscanner.back.persistence.criteria;

import java.io.Serializable;
import java.util.Collection;

public class ClassFileBoCriteria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Collection<Long> consumedClassesIds;
	private Collection<String> consumedClassesNames;
	
	public Collection<Long> getConsumedClassesIds() {
		return consumedClassesIds;
	}
	public void setConsumedClassesIds(Collection<Long> consumedClassesIds) {
		this.consumedClassesIds = consumedClassesIds;
	}
	public Collection<String> getConsumedClassesNames() {
		return consumedClassesNames;
	}
	public void setConsumedClassesNames(Collection<String> consumedClassesNames) {
		this.consumedClassesNames = consumedClassesNames;
	}	
}
