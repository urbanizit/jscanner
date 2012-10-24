package org.urbanizit.jscanner.back.persistence.dao;

import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import org.urbanizit.jscanner.back.persistence.bo.MethodBo;
import org.urbanizit.jscanner.back.persistence.itf.MethodDaoItf;


@Named
public class MethodDao extends AbstractDao<MethodBo, Long>  implements MethodDaoItf{
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<MethodBo> findMethodDependencies(final Long archiveProvider, final Long archiveCustomer) {
		
		String ejbSqlquery = " select distinct(method) "+
	      " from ArchiveBo as archive1 join archive1.classFiles as classFile1 " +
		  "      join classFile1.methods method, " +
		  "      ArchiveBo as archive2 join archive2.classFiles as classFile2" +
		  "      join classFile2.methodCalls methodCall " +
		  " where archive1.id = :archiveProvider " +
		  " and archive2.id = :archiveCustomer " +
		  " and classFile1.isInterface = true " +
		  " and methodCall.methodReference = method.methodReference ";
		
		Query query =  getEntityManager().createQuery(ejbSqlquery);
		query.setParameter("archiveProvider", archiveProvider  );	
		query.setParameter("archiveCustomer", archiveCustomer );
				
		List<MethodBo> method =  query.getResultList();
		return method;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MethodBo> findMethodDependencies(final Collection<Long> archiveProviders, final Collection<Long> archiveCustomers) {
		
		String ejbSqlquery = " select distinct(method) "+
	      " from ArchiveBo as archive1 join archive1.classFiles as classFile1 " +
		  "      join classFile1.methods method, " +
		  "      ArchiveBo as archive2 join archive2.classFiles as classFile2" +
		  "      join classFile2.methodCalls methodCall " +
		  " where archive1.id in(:archiveProvider) " +
		  " and archive2.id in(:archiveCustomer) " +
		  " and classFile1.isInterface = true " +
		  " and archive1.ownerGroup <> archive2.ownerGroup"+
		  " and methodCall.methodReference = method.methodReference ";
		
		Query query =  getEntityManager().createQuery(ejbSqlquery);
		query.setParameter("archiveProvider", archiveProviders  );	
		query.setParameter("archiveCustomer", archiveCustomers );
				
		List<MethodBo> method =  query.getResultList();
		return method;
	}
}
