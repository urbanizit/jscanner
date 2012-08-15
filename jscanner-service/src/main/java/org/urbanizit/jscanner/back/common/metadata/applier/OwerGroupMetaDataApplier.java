package org.urbanizit.jscanner.back.common.metadata.applier;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.analyser.resolver.OwnerGroupResolver;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.NestableArchive;

public class OwerGroupMetaDataApplier extends AbstractMetaDataApplier<Archive> {
	
	private Logger logger = LoggerFactory.getLogger(OwerGroupMetaDataApplier.class);
	
	/**
	 * @see org.urbanizit.jscanner.back.common.metadata.applier.AbstractMetaDataApplier#apply(java.lang.Object)
	 */
	public void apply(final Archive archive){
		
		if(archive == null){
			logger.warn("Unable to apply owner group on null archive");
		}
		
		applyOwnerGroup(archive);
	}	
		
	
	/**
	 * Apply the owner group on archive recursively
	 * 
	 * @param archive
	 */
	protected void applyOwnerGroup(final Archive archive){
		if(archive == null){
			return;
		}
		
		if(Boolean.TRUE.equals(archive.getCompagnyFile())){
			
			archive.setOwnerGroup(OwnerGroupResolver.resolveOwner(archive));
					
			if(archive instanceof NestableArchive){
				NestableArchive	nestableArchive = (NestableArchive)archive;
				
				Collection<Archive> nestedArchives = nestableArchive.getSubArchives();
				
				if(nestedArchives != null){				
					for (Archive nestedArchive : nestedArchives) {
						applyOwnerGroup(nestedArchive);
					}				
				}			
			}			
		}else{
			//TODO configurable
			archive.setOwnerGroup("Third party");
		}	
	}
}
