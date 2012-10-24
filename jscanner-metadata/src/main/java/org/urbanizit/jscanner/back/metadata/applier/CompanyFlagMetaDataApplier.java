package org.urbanizit.jscanner.back.metadata.applier;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.ClassFileBo;
import org.urbanizit.jscanner.back.persistence.bo.NestableArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.PackageNameBo;

public class CompanyFlagMetaDataApplier extends AbstractMetaDataApplier<ArchiveBo> {
	
	private Logger logger = LoggerFactory.getLogger(CompanyFlagMetaDataApplier.class);
	
	private List<String> compagnyRootsPackages = Arrays.asList("com.mycompagny", "org.mycompagny");
	
	/**
	 * @see org.urbanizit.jscanner.back.common.metadata.applier.AbstractMetaDataApplier#apply(java.lang.Object)
	 */
	public void apply(final ArchiveBo archive){
	
		if(archive == null){
			logger.warn("Unable to apply company flat MetaData on null archive");
		}		
		setCompanyMetadata(archive);		
	}	
	
	
	/**
	 * Apply the archive metadata
	 * @param archive
	 * @return
	 */
	protected boolean setCompanyMetadata(final ArchiveBo archive){
		
		if(archive == null){
			logger.warn("Unable to apply company flat MetaData on null archive");
		}
		
		boolean isCompagnyArchive = false;
		
		//Applying nested archive metadata
		if(archive instanceof NestableArchiveBo){
			NestableArchiveBo nestableArchive = (NestableArchiveBo) archive;
			
			if(CollectionUtils.isNotEmpty(nestableArchive.getSubArchives())){
				for (ArchiveBo nestedArchive : nestableArchive.getSubArchives()) {
					isCompagnyArchive |= setCompanyMetadata(nestedArchive);
				}
			} 		
		}
		
		//If archive doesn't contains company nested archive..check it
		if(!isCompagnyArchive){
			isCompagnyArchive |= isCompanyArchive(archive);
		}
		archive.setCompagnyFile(isCompagnyArchive);		
		return isCompagnyArchive;

	}
		
	/**
	 * Return true is Archive is a company Archive
	 * 
	 * @param archive
	 * @return
	 */
	protected boolean isCompanyArchive(final ArchiveBo archive){		
		
		//Searching in own jar
		if(CollectionUtils.isNotEmpty(archive.getClassFiles())){

			for (ClassFileBo classFile : archive.getClassFiles()) {				
				if(isCompanyPackage(classFile.getPackageName())){
					return true;
				}
			}			
		}	
		return false;
	}
	
	
	/**
	 * Is it a company package name ?
	 * 
	 * @param packageName
	 * @return true : it is / false otherwise
	 */
	protected boolean isCompanyPackage(final PackageNameBo packageName){

		if(packageName != null){
			for (String  compagnyRootPackage : compagnyRootsPackages) {
				if(packageName.getPackageName() != null && packageName.getPackageName().startsWith(compagnyRootPackage)){
					return true;
				}
			}
		}
		return false;
	}
}
