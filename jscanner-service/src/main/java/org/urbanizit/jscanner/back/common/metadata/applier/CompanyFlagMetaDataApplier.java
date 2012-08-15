package org.urbanizit.jscanner.back.common.metadata.applier;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.NestableArchive;

public class CompanyFlagMetaDataApplier extends AbstractMetaDataApplier<Archive> {
	
	private Logger logger = LoggerFactory.getLogger(CompanyFlagMetaDataApplier.class);
	
	private List<String> compagnyRootsPackages = Arrays.asList("com.company", "org.compagny");
	
	/**
	 * @see org.urbanizit.jscanner.back.common.metadata.applier.AbstractMetaDataApplier#apply(java.lang.Object)
	 */
	public void apply(final Archive archive){
	
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
	protected boolean setCompanyMetadata(final Archive archive){
		
		if(archive == null){
			logger.warn("Unable to apply company flat MetaData on null archive");
		}
		
		boolean isCompagnyArchive = false;
		
		//Applying nested archive metadata
		if(archive instanceof NestableArchive){
			NestableArchive nestableArchive = (NestableArchive) archive;
			
			if(CollectionUtils.isNotEmpty(nestableArchive.getSubArchives())){
				for (Archive nestedArchive : nestableArchive.getSubArchives()) {
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
	protected boolean isCompanyArchive(final Archive archive){		
		
		//Searching in own jar
		if(CollectionUtils.isNotEmpty(archive.getClassFiles())){

			for (ClassFile classFile : archive.getClassFiles()) {				
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
	protected boolean isCompanyPackage(final String packageName){

		for (String  compagnyRootPackage : compagnyRootsPackages) {
			if(packageName.startsWith(compagnyRootPackage)){
				return true;
			}
		}
		return false;
	}
}
