package org.urbanizit.jscanner.back.persistence.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.criteria.ArchiveBoCriteria;
import org.urbanizit.jscanner.back.persistence.itf.ArchiveDaoItf;
import org.urbanizit.jscanner.back.transfert.dto.ArchiveDependency;


@Named
public class ArchiveDao extends AbstractDao<ArchiveBo, Long>  implements ArchiveDaoItf{

	private Logger logger = LoggerFactory.getLogger(ArchiveDao.class);

	/**
	 * @see org.urbanizit.jscanner.back.persistence.itf.ArchiveDaoItf#findByCriteria(org.urbanizit.jscanner.back.persistence.criteria.ArchiveBoCriteria)
	 */
	@SuppressWarnings("unchecked")
	public List<ArchiveBo> findByCriteria(final ArchiveBoCriteria criteria){	
		
		String ejbSqlquery = " select distinct(archive) " +
							 " from ArchiveBo archive " +
							 " left outer join fetch archive.manifest manifest"; 
							if(	!CollectionUtils.isEmpty(criteria.getClassNames())
							||	!CollectionUtils.isEmpty(criteria.getPackageNames())
							||	!CollectionUtils.isEmpty(criteria.getMethodCalled())
							|| 	!CollectionUtils.isEmpty(criteria.getCanonicalNamesDependencies())){
								ejbSqlquery += " join archive.classFiles as classFile ";
							 }
							if(!CollectionUtils.isEmpty(criteria.getCanonicalNamesDependencies())){
							    ejbSqlquery += " join classFile.classDependencies as classDependency ";
							}
												
							if( !CollectionUtils.isEmpty(criteria.getMethodCalled())){
								 ejbSqlquery += " join classFile.methodCalls as methodCall ";
								 ejbSqlquery += " , MethodBo mc "; 
							}							
							
							ejbSqlquery += " where 1=1 ";
				
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(criteria.getArchiveNames() != null){
			Collection<String> archivesNames = criteria.getArchiveNames();
			if(archivesNames.size() == 1){
				ejbSqlquery += " and upper(archive.name) like :archiveName ";
				params.put("archiveName", criteria.getArchiveNames().toArray(new String[0])[0].replaceAll("\\*", "%").toUpperCase());
			}else{				
				ejbSqlquery += " and  upper(archive.name) in (:archiveNames) ";
				params.put("archiveNames", criteria.getArchiveNames());
			}
		}	

		if(criteria.getCompagnyFile() != null){
			ejbSqlquery += " and archive.compagnyFile = :compagnyFile ";
			params.put("compagnyFile", criteria.getCompagnyFile());	
		}
		
		if(criteria.getChecksum() != null){
			ejbSqlquery += " and archive.checksum = :checksum ";
			params.put("checksum", criteria.getChecksum());
		}	
		
		if(criteria.getArchiveIds() != null && !criteria.getArchiveIds().isEmpty()){
			ejbSqlquery += " and archive.id in (:archiveIds) ";
			params.put("archiveIds", criteria.getArchiveIds());
		}			
		
		if(!CollectionUtils.isEmpty(criteria.getClassNames())){
			ejbSqlquery += " and classFile.className.className in (:classNames) ";
			params.put("classNames", criteria.getClassNames());
		}
		
		if(!CollectionUtils.isEmpty(criteria.getPackageNames())){
			ejbSqlquery += " and classFile.packageName in (:packageNames) ";
			params.put("packageNames", criteria.getPackageNames());
		}
		
		if( !CollectionUtils.isEmpty(criteria.getMethodCalled())){
			ejbSqlquery += " and methodCall.methodReference.signature.id =  mc.signature.id  ";
			ejbSqlquery += " and mc.id in (:methodCallIds ) ";
			params.put("methodCallIds", criteria.getMethodCalled());
		}
		
		if(!CollectionUtils.isEmpty(criteria.getCanonicalNamesDependencies())){
			ejbSqlquery += " and classDependency.className in (:classNamesDependencies) ";
			params.put("classNamesDependencies", criteria.getCanonicalNamesDependencies());
		}	
		
		Query query =  getEntityManager().createQuery(ejbSqlquery);
		
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}		
				
		return query.getResultList();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<String> findProvidedClass(final Long archiveId) {
		String ejbSqlquery = " select classFile.className.className " +
							 " from ArchiveBo archive " +
							 " join archive.classFiles as classFile " +
							 " where archive.id in (:archiveId) ";
		Query query =  getEntityManager().createQuery(ejbSqlquery);
		query.setParameter("archiveId", archiveId);
		
		List<String> classNames = query.getResultList();
		return classNames;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<String> findRequiredClass(Long archiveId) {
		String ejbSqlquery = " select classDependency.className.className " +
							 " from ArchiveBo archive join archive.classFiles as classFile " +
							 " join classFile.classDependencies as classDependency  " +
							 " where archive.id in (:archiveId) ";
		Query query =  getEntityManager().createQuery(ejbSqlquery);
		query.setParameter("archiveId", archiveId);		
		List<String> classNames = query.getResultList();
		return classNames;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ArchiveDependency> findDependOnArchives( final Collection<Long> archiveIds ,  final Collection<Long> archiveWhiteListIds ){
			
		logger.debug("Begin : findDependOnArchives ");
				
		
//		String ejbSqlquery = 
//			  " select distinct  archive2.id, archive1.id " +
//		      " from ArchiveBo as archive1 join archive1.classFiles as classFile1  " +      
//		      "                            join classFile1.classDependencies as className2 "+
//		      "                            join className2.classFiles as classFile2 " +
//		      "                            join classFile2.archive as archive2  "+
//			  " where 1=1" +
//		      " and archive1.compagnyFile is true " +
//		      " and archive1.ownerGroup <> archive2.ownerGroup "; 
		
		String ejbSqlquery = 
			  " select distinct archive2.id, archive1.id " +
		      " from ArchiveBo as archive2 join archive2.classFiles as classFile2  " +
		      "                            join classFile2.className as className2  "+
		      "                            join className2.classFileDependencies as classFile1 " +
		      "                            join classFile1.archive as archive1  "+
			  " where 1=1 ";
		
		if(!CollectionUtils.isEmpty(archiveIds)){
			if(archiveIds.size() > 1){
				ejbSqlquery +=  " and archive2.id in (:archiveIds) ";
			}else{
				ejbSqlquery +=  " and archive2.id = :archiveIds ";
			}
		}
		if(archiveWhiteListIds != null && !archiveWhiteListIds.isEmpty()){
			ejbSqlquery += " and archive2.id in (:archiveWhiteListIds) ";
		}
			
		//ejbSqlquery += " and classFile1.isInterface = true";		
		//ejbSqlquery += " and archive1.compagnyFile is true ";		
		//ejbSqlquery += " and archive2.ownerGroup <> archive1.ownerGroup ";
		
		
		Query query =  getEntityManager().createQuery(ejbSqlquery);
		
		
		query.setParameter("archiveIds", (archiveIds != null && archiveIds.size() == 1) ? archiveIds.toArray()[0]:archiveIds);	
		if(archiveWhiteListIds != null && !archiveWhiteListIds.isEmpty()){
			query.setParameter("archiveWhiteListIds", archiveWhiteListIds);	
		}	
				
		List<Object[]> queryResults =  query.getResultList();
		
		//Object conversion
		List<ArchiveDependency> res = new ArrayList<ArchiveDependency>();
		if(queryResults != null){
			for (Object[] objects : queryResults) {
				res.add(new ArchiveDependency(((Long)objects[0]), ((Long)objects[1])));
			}
		}
		
		logger.debug("End : findDependOnArchives ");
		return res;
		
	}

	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ArchiveDependency> findDependArchives( final Collection<Long> archiveIds ,  final Collection<Long> archiveWhiteListIds ) {

		String ejbSqlquery = 
			  " select distinct archive1.id, archive2.id " +
		      " from ArchiveBo as archive1 join archive1.classFiles as classFile1  " +
		      "                            join classFile1.className as className1  "+
		      "                            join className1.classFileDependencies as classFile2 " +
		      "                            join classFile2.archive as archive2  "+
			  " where 1=1 ";

		
		if(!CollectionUtils.isEmpty(archiveIds)){
			if(archiveIds.size() > 1){
				ejbSqlquery +=  " and archive2.id in (:archiveIds) ";
			}else{
				ejbSqlquery +=  " and archive2.id = :archiveIds ";
			}
		}
		if(!CollectionUtils.isEmpty(archiveWhiteListIds)){
			ejbSqlquery +=  " and archive1.id in (:archiveWhiteListIds) ";
		}
		
		//ejbSqlquery += " and classFile1.isInterface = true";		
		//ejbSqlquery += " and archive1.compagnyFile is true ";		
		//ejbSqlquery += " and archive2.ownerGroup <> archive1.ownerGroup ";
		
		Query query =  getEntityManager().createQuery(ejbSqlquery);
		if(!CollectionUtils.isEmpty(archiveIds)){
			query.setParameter("archiveIds", archiveIds);
		}
		if(!CollectionUtils.isEmpty(archiveWhiteListIds)){
			query.setParameter("archiveWhiteListIds", archiveWhiteListIds);	
		}	
		
		
		List<Object[]> queryResults =  query.getResultList();
		
		//Object conversion
		List<ArchiveDependency> res = new ArrayList<ArchiveDependency>();
		if(queryResults != null){
			for (Object[] objects : queryResults) {
				res.add(new ArchiveDependency(((Long)objects[0]), ((Long)objects[1])));
			}
		}
		return res;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ArchiveDependency> findConflictualArchives(final Collection<Long> archiveIds, final Collection<Long> archiveWhiteListIds) {
		
		String ejbSqlquery = 
			  " select distinct  archive1.id, archive2.id  " +
		      " from ArchiveBo as archive1 left outer join archive1.classFiles as classFile1, " +
			  "      ArchiveBo as archive2 left outer join archive2.classFiles as classFile2  " +
			  " where archive1.id < archive2.id" +			  
			  " and classFile2.className = classFile1.className " +			  
			  " and archive1.id in (:archiveIds) ";
		if(archiveWhiteListIds != null && !archiveWhiteListIds.isEmpty()){
			ejbSqlquery +=  " and archive2.id in (:archiveWhiteListIds) ";
		}
	
				
		Query query =  getEntityManager().createQuery(ejbSqlquery);
		query.setParameter("archiveIds", archiveIds);	
		if(archiveWhiteListIds != null && !archiveWhiteListIds.isEmpty()){
			query.setParameter("archiveWhiteListIds", archiveWhiteListIds);	
		}
		List<Object[]> queryResults =  query.getResultList();
		
		//Object conversion
		List<ArchiveDependency> res = new ArrayList<ArchiveDependency>();
		if(queryResults != null){
			for (Object[] objects : queryResults) {
				res.add(new ArchiveDependency(((Long)objects[0]), ((Long)objects[1])));
			}
		}
		return res;
	}
	

}
