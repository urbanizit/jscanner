package org.urbanizit.jscanner.back.persistence.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import org.urbanizit.jscanner.back.persistence.bo.ClassFileBo;
import org.urbanizit.jscanner.back.persistence.itf.ClassFileDaoItf;


@Named
public class ClassFileDao extends AbstractDao<ClassFileBo, Long>  implements ClassFileDaoItf{

	@SuppressWarnings("unchecked")
	public List<ClassFileBo> findDependClass(final Long classFileId){
		
		String ejbSqlquery = " select distinct(cfc) "+ 
		" from ClassFileBo cfp, "+ 
		" ClassFileBo cfc "+
		" left join cfc.classDependencies as cdCustomer "+ 	
		" where cfp.canonicalName = cdCustomer " +
		" and cfp.id = :classFileId";
		Query query =  getEntityManager().createQuery(ejbSqlquery);	
		query.setParameter("classFileId", classFileId);
		
				
		return query.getResultList();
	}


}
