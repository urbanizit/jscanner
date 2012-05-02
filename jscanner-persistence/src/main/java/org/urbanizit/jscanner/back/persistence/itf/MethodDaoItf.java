package org.urbanizit.jscanner.back.persistence.itf;

import java.util.Collection;
import java.util.List;

import org.urbanizit.jscanner.back.persistence.bo.MethodBo;


public interface MethodDaoItf extends GenericDaoItf<MethodBo, Long> {

	/**
	 * Give methods used in a archive by an other archive.
	 * 
	 * @param archiveProvider ID archive providing services
	 * @param archiveCustomer ID archive consuming services
	 * @return List consumed methods
	 */
	public List<MethodBo> findMethodDependencies(final Long archiveProvider, final Long archiveCustomer);
	
	
	public List<MethodBo> findMethodDependencies(final Collection<Long> archiveProviders, final Collection<Long> archiveCustomers);

}
