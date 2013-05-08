package org.urbanizit.jscanner.back.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.urbanizit.jscanner.back.common.converter.Bo2DtoIConverter;
import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.ClassFileBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodBo;
import org.urbanizit.jscanner.back.persistence.bo.NestableArchiveBo;
import org.urbanizit.jscanner.back.persistence.itf.ArchiveDaoItf;
import org.urbanizit.jscanner.back.persistence.itf.ClassFileDaoItf;
import org.urbanizit.jscanner.back.persistence.itf.MethodDaoItf;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.NestableArchive;
import org.urbanizit.jscanner.transfert.itf.CatalogServiceItf;
import org.urbanizit.jscanner.transfert.view.ArchiveView;


/**
 * Service dedicated to element catalog.
 * @author ldassonville
 */
@Service @Named("CatalogServiceImpl")
@Transactional
public class CatalogServiceImpl implements CatalogServiceItf{

	@Inject private ArchiveDaoItf archiveDao;
	@Inject private ClassFileDaoItf classFileDao;
	@Inject private MethodDaoItf methodDao;
	
	private final Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class);

	
	public Archive getArchive(final Long archiveId, final List<ArchiveView> views)	throws Exception {

		List<ArchiveView> workingView = views;
		
		ArchiveBo archive  = archiveDao.findById(archiveId);		
		if(archive == null){
			logger.debug("Unable to find archive with id {}",archiveId);
			return null;
		}
		Archive res =  Bo2DtoIConverter.convert(archive);			
		if(workingView.contains(ArchiveView.FIRST_LEVEL_CLASS)){	
			workingView = new ArrayList<ArchiveView>(workingView);
			res.setClassFiles(Bo2DtoIConverter.convertClassFiles(archive.getClassFiles()));
			workingView.remove(ArchiveView.FIRST_LEVEL_CLASS);
		}
		
		if((archive instanceof NestableArchiveBo) && views != null && views.contains(ArchiveView.EMBBEDED_ARCHIVES)){
			NestableArchiveBo nestableArchive = (NestableArchiveBo)archive;
			NestableArchive nestableArchiveDtoI = (NestableArchive)res;
			List<Archive> subArchives = new ArrayList<Archive>();			
			for (ArchiveBo subArchive : nestableArchive.getSubArchives()) {
				subArchives.add(getArchive(subArchive.getId(), workingView));
			}
			nestableArchiveDtoI.setSubArchives(subArchives);
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
	
	public Method getMethod(Long methodId) throws Exception{
		Method res = null;
		MethodBo method= methodDao.findById(methodId);
				
		res = Bo2DtoIConverter.convert(method);
			
		return res;
	}
}
