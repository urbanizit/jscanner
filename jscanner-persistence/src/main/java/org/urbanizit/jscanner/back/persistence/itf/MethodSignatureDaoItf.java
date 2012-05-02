package org.urbanizit.jscanner.back.persistence.itf;


import java.util.Collection;
import java.util.List;

import org.urbanizit.jscanner.back.persistence.bo.MethodSignatureBo;


public interface MethodSignatureDaoItf extends GenericDaoItf<MethodSignatureBo, Long> {
	
	/**
	 * Find a className
	 * @param className
	 * @return
	 */
	MethodSignatureBo findByMethodSignature(final String methodSignature);
	
	
	/**
	 * Find className in list
	 * @param classNames
	 * @return
	 */
	List<MethodSignatureBo> findByMethodSignatureIn(final Collection<String> methodSignatures);
}
