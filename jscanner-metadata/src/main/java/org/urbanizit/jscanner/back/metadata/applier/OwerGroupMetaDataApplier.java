package org.urbanizit.jscanner.back.metadata.applier;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.analyser.resolver.OwnerGroupResolver;
import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.NestableArchiveBo;

public class OwerGroupMetaDataApplier extends AbstractMetaDataApplier<ArchiveBo> {
	
	private Logger logger = LoggerFactory.getLogger(OwerGroupMetaDataApplier.class);
	
	/**
	 * @see org.urbanizit.jscanner.back.common.metadata.applier.AbstractMetaDataApplier#apply(java.lang.Object)
	 */
	public void apply(final ArchiveBo archive){
		
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
	protected void applyOwnerGroup(final ArchiveBo archive){
		if(archive == null){
			return;
		}
		
		if(Boolean.TRUE.equals(archive.getCompagnyFile())){
			
			archive.setOwnerGroup(OwnerGroupResolver.resolveOwner(archive));
					
			if(archive instanceof NestableArchiveBo){
				NestableArchiveBo	nestableArchive = (NestableArchiveBo)archive;
				
				Collection<ArchiveBo> nestedArchives = nestableArchive.getSubArchives();
				
				if(nestedArchives != null){				
					for (ArchiveBo nestedArchive : nestedArchives) {
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
