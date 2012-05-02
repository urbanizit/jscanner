package org.urbanizit.jscanner.back.persistence.itf;


import java.util.Collection;
import java.util.List;

import org.urbanizit.jscanner.back.persistence.bo.MethodReferenceBo;


public interface MethodReferenceDaoItf extends GenericDaoItf<MethodReferenceBo, Long> {
	
	/**
	 * Find a method reference
	 * @param className
	 * @param methodSignature
	 * @return
	 */
	MethodReferenceBo findByClassNameAndSignature(final Long className, final Long methodSignature);
	
	/**
	 * Find a method reference
	 * @param className
	 * @param methodSignature
	 * @return
	 */
	List<MethodReferenceBo> findByHashCodeIn(final Collection<String> hashcodes);
}
