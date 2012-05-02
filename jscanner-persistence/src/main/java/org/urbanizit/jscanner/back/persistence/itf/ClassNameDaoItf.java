package org.urbanizit.jscanner.back.persistence.itf;


import java.util.Collection;
import java.util.List;

import org.urbanizit.jscanner.back.persistence.bo.ClassNameBo;


public interface ClassNameDaoItf extends GenericDaoItf<ClassNameBo, Long> {
	
	/**
	 * Find a className
	 * @param className
	 * @return
	 */
	ClassNameBo findByClassName(final String className);
	
	
	/**
	 * Find className in list
	 * @param classNames
	 * @return
	 */
	List<ClassNameBo> findByClassNameIn(final Collection<String> classNames);
}
