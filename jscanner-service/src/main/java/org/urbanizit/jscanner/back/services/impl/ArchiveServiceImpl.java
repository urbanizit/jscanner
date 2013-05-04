package org.urbanizit.jscanner.back.services.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.urbanizit.jscanner.back.common.converter.Bo2DtoIConverter;
import org.urbanizit.jscanner.back.common.converter.DtoI2BoConverter;
import org.urbanizit.jscanner.back.common.resolver.ArchiveClassNameResolver;
import org.urbanizit.jscanner.back.common.resolver.ArchiveMethodSignatureResolver;
import org.urbanizit.jscanner.back.common.resolver.ArchivePackageResolver;
import org.urbanizit.jscanner.back.common.resolver.pojo.MethodSignatureIdentifier;
import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.ClassFileBo;
import org.urbanizit.jscanner.back.persistence.bo.ClassNameBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodCallBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodSignatureBo;
import org.urbanizit.jscanner.back.persistence.bo.NestableArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.PackageNameBo;
import org.urbanizit.jscanner.back.persistence.criteria.ArchiveBoCriteria;
import org.urbanizit.jscanner.back.persistence.itf.ArchiveDaoItf;
import org.urbanizit.jscanner.back.persistence.itf.ClassNameDaoItf;
import org.urbanizit.jscanner.back.persistence.itf.MethodSignatureDaoItf;
import org.urbanizit.jscanner.back.persistence.itf.PackageNameDaoItf;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ArchiveCriteria;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.MethodCall;
import org.urbanizit.jscanner.transfert.NestableArchive;
import org.urbanizit.jscanner.transfert.itf.ArchiveServiceItf;


/**
 * Service dedicated to archives.
 * @author ldassonville
 */
@Service @Named("ArchiveServiceImpl")
public class ArchiveServiceImpl implements ArchiveServiceItf{

	private static final long serialVersionUID = 1L;
	
	@Inject private ArchiveDaoItf archiveDao;
	@Inject private PackageNameDaoItf packageNameDao;
	@Inject private ClassNameDaoItf classNameDao;
	@Inject private MethodSignatureDaoItf methodSignatureDao;
	@Autowired private ApplicationContext applicationContext;
	
	private final Logger logger = LoggerFactory.getLogger(ArchiveServiceImpl.class);
	
	private ArchiveServiceItf getTransactionProxy(){
		return applicationContext.getBean(ArchiveServiceItf.class);
	}
		
	public List<Archive> findArchiveByCriteria(ArchiveCriteria criteriaDtoI)throws Exception {
		if(criteriaDtoI == null){
			throw new Exception("Archive required");
		}
		
		ArchiveBoCriteria criteria = DtoI2BoConverter.convert(criteriaDtoI);
				
		Collection<ArchiveBo> archives = archiveDao.findByCriteria(criteria);
		
		return Bo2DtoIConverter.convertArchives(archives);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Long registerArchive(Archive archiveDtoI)throws Exception {
	
		if(archiveDtoI == null){
			throw new Exception("Archive is null");
		}
		
		ArchiveBoCriteria criteria = new ArchiveBoCriteria();
		criteria.setChecksum(archiveDtoI.getChecksum());		
		List<ArchiveBo> archives = archiveDao.findByCriteria(criteria);
		
		if(!archives.isEmpty()){
			logger.info("Archive {} already exist. Register ignored", archiveDtoI);
			return archives.get(0).getId();
		}

		return saveArchive(archiveDtoI);
	}
		
	
	/**
	 * Save a class File
	 * @param classFileDtoI
	 * @param archive
	 * @param packageNameCache
	 * @param classNameCache
	 * @return Saved class File
	 */
	public ClassFileBo saveClassFile(ClassFile classFileDtoI, ArchiveBo archive, 
				Map<String, PackageNameBo> packageNameCache, 
				Map<String, ClassNameBo> classNameCache, 
				Map<MethodSignatureIdentifier, MethodSignatureBo> methodSignatureCache){
		

		ClassFileBo classFile = DtoI2BoConverter.convert(classFileDtoI);		
		classFile.setClassName(classNameCache.get(classFileDtoI.getCanonicalName()));
		classFile.setPackageName(packageNameCache.get(classFileDtoI.getPackageName()));
		classFile.setArchive(archive);
				
		//Classes dependencies conversion
		if(classFileDtoI.getClassDependencies() != null){
			for (String strClassName : classFileDtoI.getClassDependencies()) {
				classFile.addClassDependency( classNameCache.get(strClassName));
			}
		}		
		classFileDtoI.getClassDependencies();
				
		//Packages dependencies conversion
		if(classFileDtoI.getPackageDependencies() != null){
			for (String strPackageName : classFileDtoI.getPackageDependencies() ){
				if(strPackageName == null || strPackageName.isEmpty()){
					continue;
				}						
				classFile.addPackageDependency(packageNameCache.get(strPackageName));
			}
		}	
	
		//Method conversion
		if(classFileDtoI.getMethods() != null){
			for(Method methodDtoI : classFileDtoI.getMethods()){
				MethodBo method = DtoI2BoConverter.convert(methodDtoI);
				method.setSignature(methodSignatureCache.get(new MethodSignatureIdentifier(classFileDtoI.getCanonicalName(), methodDtoI.getMethodSignature())));
				classFile.addMethod(method);
			}
		}
		
		//Method call conversion
		if(classFileDtoI.getMethodCalls() != null){
			for(MethodCall methodCallDtoI : classFileDtoI.getMethodCalls()){
				MethodCallBo methodCall = DtoI2BoConverter.convert(methodCallDtoI);				
				methodCall.setSignature(methodSignatureCache.get(new MethodSignatureIdentifier(methodCallDtoI.getClassName(), methodCallDtoI.getMethodSignature())));
				classFile.addMethodCall(methodCall);	
			}
		}
		return classFile;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Long saveArchiveNonRecursif(Archive archiveDtoI )throws Exception{
		
		logger.debug("saveArchiveNonRecursif : {}", archiveDtoI.getName());
		
		//Is archive already exist ?
		ArchiveBoCriteria archiveCriteria = new ArchiveBoCriteria();
		archiveCriteria.setChecksum(archiveDtoI.getChecksum());
		List<ArchiveBo> archives = archiveDao.findByCriteria(archiveCriteria);
		if(!archives.isEmpty()){		
			return archives.get(0).getId();
		}
		
		Long time = System.currentTimeMillis();
		Long progession = System.currentTimeMillis();
		
		Map<String, PackageNameBo> packageNameCache = new ArchivePackageResolver(packageNameDao, false).resolve(archiveDtoI);		
		progession = System.currentTimeMillis();		
		logger.debug("time calculating packages : Resolving {} elements in {}s", packageNameCache.size(), ((progession - time) /1000));
		
		time = System.currentTimeMillis();
		Map<String, ClassNameBo> classNameCache = new ArchiveClassNameResolver(classNameDao, false).resolve(archiveDtoI);
		progession = System.currentTimeMillis();		
		logger.debug("time calculating classNames : Resolving {} elements in {}s", classNameCache.size(),  (progession - time) /1000);
		
		time = System.currentTimeMillis();
		Map<MethodSignatureIdentifier, MethodSignatureBo> methodSignatureCache = new ArchiveMethodSignatureResolver(methodSignatureDao, classNameDao, classNameCache, false).resolve(archiveDtoI);
		progession = System.currentTimeMillis();		
		logger.debug("time calculating methodSignature : Resolving {} elements in {}s", methodSignatureCache.size(), (progession - time) /1000);
			
		ArchiveBo archive = DtoI2BoConverter.convert(archiveDtoI);
		archive.setRegistrationDate(new Date());
		
		time = System.currentTimeMillis();
		logger.debug("Begin : saveArchiveNonRecursif {}", archiveDtoI.getName());
		
		
		//fining sub-archives (assume they already exist)
		if(archiveDtoI instanceof NestableArchive){	
			
			NestableArchive nestableArchiveDtoI = (NestableArchive)archiveDtoI;
			
			List<Archive> subArchives = nestableArchiveDtoI.getSubArchives();
			if(subArchives != null && !subArchives.isEmpty()){				
				for (Archive subArchiveDtoI : subArchives) {
					archiveCriteria = new ArchiveBoCriteria();
					archiveCriteria.setChecksum(subArchiveDtoI.getChecksum());
					List<ArchiveBo> subArchivesBo = archiveDao.findByCriteria(archiveCriteria);
					if(subArchivesBo != null && !subArchivesBo.isEmpty()){
						((NestableArchiveBo)archive).addSubArchive(subArchivesBo.get(0));
					}else{
						throw new Exception("Unable to find sub archive");
					}					
				}
			}		
		}	
		
		//Adding archive classFile		
		if(archiveDtoI.getClassFiles() != null){
			for (ClassFile classFileDtoI : archiveDtoI.getClassFiles()) {
				archive.addClassFiles(saveClassFile(classFileDtoI, archive, packageNameCache, classNameCache, methodSignatureCache));
			}
		}
		archive =  archiveDao.save(archive);	
		
		logger.debug("End : saveArchiveNonRecursif {} in {}s", archiveDtoI.getName(), TimeUnit.MILLISECONDS.toSeconds(time - System.currentTimeMillis()));
		return archive.getId();
		
	}
	
	
	/**
	 * @see org.urbanizit.jscanner.transfert.interne.itf.ArchiveServiceItf#saveArchive(org.urbanizit.jscanner.transfert.interne.Archive, java.util.Map, java.util.Map)
	 */
	public Long saveArchive(Archive archiveDtoI)throws Exception{
		
		ArchiveBoCriteria archiveCriteria = new ArchiveBoCriteria();
		archiveCriteria.setChecksum(archiveDtoI.getChecksum());
		List<ArchiveBo> archives = archiveDao.findByCriteria(archiveCriteria);
		if(!archives.isEmpty()){		
			return archives.get(0).getId();
		}
		
		Long time = System.currentTimeMillis();
		logger.debug("Begin : saveArchive {}", archiveDtoI.getName());

		//Saving recursively sub-archives
		if(archiveDtoI instanceof NestableArchive){	
			
			NestableArchive nestableArchiveDtoI = (NestableArchive)archiveDtoI;
			
			List<Archive> subArchives = nestableArchiveDtoI.getSubArchives();
			if(subArchives != null){				
				for (Archive subArchiveDtoI : subArchives) {
					saveArchive(subArchiveDtoI);
				}				
			}
		}	
		Long archiveId = getTransactionProxy().saveArchiveNonRecursif(archiveDtoI);
		
		logger.debug("End : saveArchive {} in {}s", archiveDtoI.getName(), TimeUnit.MILLISECONDS.toSeconds(time - System.currentTimeMillis()));
		return archiveId;
	}

}
