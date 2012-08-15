package org.urbanizit.jscanner.back.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.urbanizit.jscanner.back.common.converter.Bo2DtoIConverter;
import org.urbanizit.jscanner.back.common.converter.DtoI2BoConverter;
import org.urbanizit.jscanner.back.common.utils.CollectionsUtils;
import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.ClassFileBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodBo;
import org.urbanizit.jscanner.back.persistence.bo.NestableArchiveBo;
import org.urbanizit.jscanner.back.persistence.criteria.ArchiveBoCriteria;
import org.urbanizit.jscanner.back.persistence.itf.ArchiveDaoItf;
import org.urbanizit.jscanner.back.persistence.itf.ClassFileDaoItf;
import org.urbanizit.jscanner.back.persistence.itf.MethodDaoItf;
import org.urbanizit.jscanner.back.transfert.dto.ArchiveDependency;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ArchiveCriteria;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.Graph;
import org.urbanizit.jscanner.transfert.Link;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.NestableArchive;
import org.urbanizit.jscanner.transfert.enums.LinkType;
import org.urbanizit.jscanner.transfert.itf.AnalyseServiceItf;


/**
 * Service dedicated to archives.
 * @author ldassonville
 */
@Service @Named("AnalyseServiceImpl")
@Transactional
public class AnalyseServiceImpl implements AnalyseServiceItf{

	@Inject private ArchiveDaoItf archiveDao;
	@Inject private ClassFileDaoItf classFileDao;
	@Inject private MethodDaoItf methodDao;
	
	private final Logger logger = LoggerFactory.getLogger(AnalyseServiceImpl.class);

	
	public Graph getDependencyGraph(final ArrayList<Long> archiveIds, final ArrayList<Long> archiveWhiteListIds) throws Exception {
		
//		if(archiveIds == null || archiveIds.isEmpty()){
//			throw new Exception("Archives Ids required");
//		}		
		Graph res = new Graph();		
		
		List<Long> extendedArchiveIds = extendsArchiveList(archiveIds);
		List<Long> extendedWhiteListIds = extendsArchiveList(archiveWhiteListIds);
		
		Set<Long> graphArchivesIds = new HashSet<Long>();	
		if(extendedArchiveIds != null){
			graphArchivesIds.addAll(extendedArchiveIds);
		}
		
		List<ArchiveDependency> archiveDependencies = archiveDao.findDependArchives(extendedArchiveIds, extendedWhiteListIds);
		
		if(archiveDependencies != null){
			
			for (ArchiveDependency archiveDependency : archiveDependencies) {
				res.addLink(new Link(archiveDependency.getCustomerId(), archiveDependency.getProviderId(), LinkType.DEPEND));
				graphArchivesIds.add(archiveDependency.getCustomerId());	
				graphArchivesIds.add(archiveDependency.getProviderId());
			}
			
			if(!CollectionUtils.isEmpty(extendedWhiteListIds)){				
				graphArchivesIds.addAll(extendedWhiteListIds);				
			}
		}	
		
		List<Archive> archives = new ArrayList<Archive>();
		res.setArchives(archives);
		for (Long archiveId : graphArchivesIds) {	
			archives.add( Bo2DtoIConverter.convert(archiveDao.findById(archiveId)));			
		}
		return res;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public List<Archive> findDependArchives(final Long archiveId, final ArrayList<Long> archiveIdsWhiteList)throws Exception {
		
		if(archiveId == null){
			throw new Exception("Archive Id required");
		}
		Set<ArchiveBo> res = new HashSet<ArchiveBo>();
		
		List<Long> extendedList = extendsArchiveList(Arrays.asList(archiveId));
		List<ArchiveDependency> archivesDependencies = archiveDao.findDependArchives(extendedList, archiveIdsWhiteList);
		
		Set<Long> archivesIds = new HashSet<Long>();
		if(archivesDependencies != null){
			for (ArchiveDependency archiveDependency : archivesDependencies) {
				archivesIds.add(archiveDependency.getCustomerId());
			}
		}		
		List<List<Long>> splitedArchiveIdsList = CollectionsUtils.splitList(archivesIds, 500);
		
		for (List<Long> subList : splitedArchiveIdsList) {
			ArchiveBoCriteria archiveCriteria = new ArchiveBoCriteria();
			archiveCriteria.setArchiveIds(subList);
			res.addAll(archiveDao.findByCriteria(archiveCriteria));
		}		
		return Bo2DtoIConverter.convertArchives(res);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Archive> findDependOnArchives(final Long archiveId, final ArrayList<Long> archiveIdsWhiteList)throws Exception {
		
		if(archiveId == null){
			throw new Exception("Archive Id required");
		}
		Set<ArchiveBo> res = new HashSet<ArchiveBo>();
		 	
		List<Long> extendedList = extendsArchiveList(Arrays.asList(archiveId));
		List<ArchiveDependency> archivesDependencies = archiveDao.findDependOnArchives(extendedList, archiveIdsWhiteList);
		
		Set<Long> archivesIds = new HashSet<Long>();
		if(archivesDependencies != null){
			for (ArchiveDependency archiveDependency : archivesDependencies) {
				archivesIds.add(archiveDependency.getCustomerId());
			}
		}		
		List<List<Long>> splitedArchiveIdsList = CollectionsUtils.splitList(archivesIds, 500);
		
		for (List<Long> subList : splitedArchiveIdsList) {
			ArchiveBoCriteria archiveCriteria = new ArchiveBoCriteria();
			archiveCriteria.setArchiveIds(subList);
			res.addAll(archiveDao.findByCriteria(archiveCriteria));
		}
		return Bo2DtoIConverter.convertArchives(res);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Set<String> findRequiredClassNames(final Long archiveId, final Boolean cascade) throws Exception {
		if(archiveId == null){
			throw new Exception("Archive Id required");
		}	
		Set<String> res = new HashSet<String>();
		
		if(Boolean.TRUE.equals(cascade)){
			ArchiveBo archive = archiveDao.findById(archiveId);
			
			//If there is sub-archives, search required classes by sub-archives
			if(archive instanceof NestableArchiveBo){				
				NestableArchiveBo  nestedArchive = (NestableArchiveBo)archive;
				
				if(nestedArchive.getSubArchives() != null){
					for (ArchiveBo subArchive : nestedArchive.getSubArchives()) {
						Set<String> subProvidedClass = findRequiredClassNames(subArchive.getId(), true); 
						if(subProvidedClass != null){
							res.addAll(subProvidedClass);
						}
					}
				}			
			}
		}
		res.addAll(archiveDao.findRequiredClass(archiveId));
		
		return res;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public Set<String> findProvidedClassNames(final Long archiveId, final Boolean cascade) throws Exception {
		if(archiveId == null){
			throw new Exception("Archive Id required");
		}
		Set<String> res = new HashSet<String>();
		
		if(Boolean.TRUE.equals(cascade)){
			ArchiveBo archive = archiveDao.findById(archiveId);
			
			//If there is sub-archives, search provided classes by sub-archives
			if(archive instanceof NestableArchiveBo){				
				NestableArchiveBo  nestedArchive = (NestableArchiveBo)archive;
				
				if(nestedArchive.getSubArchives() != null){
					for (ArchiveBo subArchive : nestedArchive.getSubArchives()) {
						Set<String> subProvidedClass = findProvidedClassNames(subArchive.getId(), true); 
						if(subProvidedClass != null){
							res.addAll(subProvidedClass);
						}
					}
				}			
			}		
		}
		res.addAll(archiveDao.findProvidedClass(archiveId));
		
		return res;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public Graph getConfictGraph(final ArrayList<Long> archiveIds, final ArrayList<Long> archiveWhiteListIds) throws Exception{

		
		if(archiveIds == null || archiveIds.isEmpty()){
			throw new Exception("Archives Ids required");
		}		
		Graph res = new Graph();		
		
		Set<Long> archivesIds = new HashSet<Long>(archiveIds);				
		List<ArchiveDependency> archiveDependencies = archiveDao.findConflictualArchives(archiveIds, archiveWhiteListIds);
			
		if(archiveDependencies != null){
			if(CollectionUtils.isEmpty(archiveWhiteListIds)){
				for (ArchiveDependency archiveDependency : archiveDependencies) {
					res.addLink(new Link(archiveDependency.getCustomerId(), archiveDependency.getProviderId(), LinkType.CONFLICTUAL));					
					archivesIds.add(archiveDependency.getCustomerId());	
					archivesIds.add(archiveDependency.getProviderId());	
				}
			}else{
				for (ArchiveDependency archiveDependency : archiveDependencies) {
					res.addLink(new Link(archiveDependency.getCustomerId(), archiveDependency.getProviderId(), LinkType.CONFLICTUAL));
				}
			}
		}	

		List<Archive> archives = new ArrayList<Archive>();
		res.setArchives(archives);
		for (Long archiveId : archiveIds) {	
			archives.add( Bo2DtoIConverter.convert(archiveDao.findById(archiveId)));			
		}
		return res;
	}
	
	
	public List<Long> extendsArchiveList(List<Long> archivesIds) throws Exception{
		
		if(archivesIds == null){
			return null;
		}
		
		List<Long> extendedList = new ArrayList<Long>();	
		for (Long archiveId : archivesIds) {
			extendedList.addAll(getArchiveComposition(archiveId));
		}	
		return extendedList;
	}
	
	private List<Long> getArchiveComposition(final Long archive) throws Exception{
		
		List<Long> archivesIds = new ArrayList<Long>();		
		getArchiveComposition(archive, archivesIds);

		return archivesIds;
	}
	
	private void getArchiveComposition(final Long archiveId, final List<Long> idsAccumulator) throws Exception{
		
		ArchiveBo archive = archiveDao.findById(archiveId);
		if(archive instanceof NestableArchiveBo){		
			for (ArchiveBo subArchive : ((NestableArchiveBo) archive).getSubArchives()) {
				getArchiveComposition(subArchive.getId(), idsAccumulator);
			}			
		}
		idsAccumulator.add(archiveId);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<String> getConfictClasses(final Long archive1Id, final Long archive2Id) throws Exception{
				
		if(archive1Id == null || archive2Id == null){
			throw new Exception("ArchiveId required");
		}	
		List<String> res =new ArrayList<String>();
		 
		Set<String> providedArchive1Class = findProvidedClassNames(archive1Id, true);
		Set<String> providedArchive2Class = findProvidedClassNames(archive2Id, true);
		
		Collection<String> conflictClasses =  CollectionUtils.intersection(providedArchive1Class, providedArchive2Class);
		 
		if(conflictClasses != null){
			res.addAll(conflictClasses);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public List<String> getDependencyClasses(final Long archive1Id, final Long archive2Id) throws Exception{
		
		if(archive1Id == null || archive2Id == null){
			throw new Exception("ArchiveId required");
		}	
		List<String> res =new ArrayList<String>();
		 
		Set<String> providedArchive1Class = findRequiredClassNames(archive1Id, true);
		Set<String> providedArchive2Class = findProvidedClassNames(archive2Id, true);
		
		Collection<String> dependencyClasses =  CollectionUtils.intersection(providedArchive1Class, providedArchive2Class);
		 
		if(dependencyClasses != null){
			res.addAll(dependencyClasses);
		}
		return res;
	}
	
	
	public List<ClassFile> getClasseUser(final Long classFileId) throws Exception{
		
		if(classFileId == null){
			throw new Exception("classFileId required");
		}	
		
		List<ClassFile> res = new ArrayList<ClassFile>();
		
		List<ClassFileBo> classFiles = classFileDao.findDependClass(classFileId);
		
		if(classFiles != null){
			for (ClassFileBo classFile : classFiles) {
				res.add(Bo2DtoIConverter.convert(classFile));
			}
		}
		return res;
	}

	public Archive getArchive(Long archiveId, Boolean deepSearch)	throws Exception {
		
		ArchiveBo archive  = archiveDao.findById(archiveId);		
		if(archive == null){
			logger.debug("Unable to find archive with id {}",archiveId);
			return null;
		}
		Archive res =  Bo2DtoIConverter.convert(archive);			
		res.setClassFiles(Bo2DtoIConverter.convertClassFiles(archive.getClassFiles()));
		
		if(Boolean.TRUE.equals(deepSearch) && (archive instanceof NestableArchiveBo)){
			NestableArchiveBo nestableArchive = (NestableArchiveBo)archive;
			NestableArchive nestableArchiveDtoI = (NestableArchive)res;
			List<Archive> subArchives = new ArrayList<Archive>();			
			for (ArchiveBo subArchive : nestableArchive.getSubArchives()) {
				subArchives.add(getArchive(subArchive.getId(), deepSearch));
			}
			nestableArchiveDtoI.setSubArchives(subArchives);
		}
		return res;
	}

	public List<Archive> findArchiveByCriteria(ArchiveCriteria archiveCriteria, Boolean deepSearch)	throws Exception {
		List<Archive> res = new ArrayList<Archive>();
		
		List<ArchiveBo> archives  = archiveDao.findByCriteria(DtoI2BoConverter.convert(archiveCriteria));
		
		for (ArchiveBo archive : archives) {
			res.add(Bo2DtoIConverter.convert(archive, Boolean.TRUE.equals(deepSearch)));
		}		
		return res;
	}

	@Override
	public List<Method> getDependencyMethods(Long archiveProvider, Long archiveCustomer) throws Exception {
		
		List<Long> extendedCustomer  = extendsArchiveList(Arrays.asList(archiveCustomer));
		List<MethodBo> methods = methodDao.findMethodDependencies(Arrays.asList(archiveProvider), extendedCustomer);
		
		List<Method> res = new ArrayList<Method>();
		
		if(methods != null){
			for (MethodBo method : methods) {
				res.add(Bo2DtoIConverter.convert(method));
			}			
		}		
		return res;
	}	
	

	public ClassFile getClassFile(Long classFileId) throws Exception{
		ClassFile res = null;
		ClassFileBo classFile = classFileDao.findById(classFileId);
		
		
		res = Bo2DtoIConverter.convert(classFile);
		res.setMethods(Bo2DtoIConverter.convertMethods(classFile.getMethods()));
		res.setMethodCalls(Bo2DtoIConverter.convertMethodCalls(classFile.getMethodCalls()));
		
		return res;
	}
}
