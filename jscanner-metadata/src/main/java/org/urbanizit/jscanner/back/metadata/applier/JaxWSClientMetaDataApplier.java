package org.urbanizit.jscanner.back.metadata.applier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.ClassFileBo;
import org.urbanizit.jscanner.back.persistence.bo.ClassNameBo;

public class JaxWSClientMetaDataApplier extends AbstractMetaDataApplier<ArchiveBo> {
	
	private Logger logger = LoggerFactory.getLogger(JaxWSClientMetaDataApplier.class);
	
	private List<String> jawxsClassNames = Arrays.asList("javax.xml.ws.WebServiceClient", "org.compagny");
	
	/**
	 * @see org.urbanizit.jscanner.back.common.metadata.applier.AbstractMetaDataApplier#apply(java.lang.Object)
	 */
	public void apply(final ArchiveBo archive){
	
		if(archive == null){
			logger.warn("Unable to apply JaxWS flag MetaData on null archive");
		}		
		setJaxWSMetadata(archive);		
	}	
	
	
	/**
	 * Apply the archive metadata
	 * @param archive
	 * @return
	 */
	protected boolean setJaxWSMetadata(final ArchiveBo archive){
		
		if(archive == null){
			logger.warn("Unable to apply JaxWS flag MetaData on null archive");
		}
		
		boolean isJaxWSClientArchive = isJawWSClientArchive(archive);
		archive.setWsArtifact(isJaxWSClientArchive);	
		
		return isJaxWSClientArchive;
	}
		
	/**
	 * Return true is Archive is a company Archive
	 * 
	 * @param archive
	 * @return
	 */
	protected boolean isJawWSClientArchive(final ArchiveBo archive){		
		
		Set<String> classDependencies = new HashSet<String>();
		
		//Collecting all class dependencies
		if(CollectionUtils.isNotEmpty(archive.getClassFiles())){			
			for (ClassFileBo classFile : archive.getClassFiles()) {	
				if(classFile.getClassDependencies() != null){
					
					if(classFile.getClassDependencies() != null){
						for (ClassNameBo className: classFile.getClassDependencies() ) {
							classDependencies.add(className.getClassName());
						}						
					}
				}
			}				
		}	
		return containsJawWSClientClass(classDependencies);
	}
	
	
	/**
	 * Is it a company package name ?
	 * 
	 * @param packageName
	 * @return true : it is / false otherwise
	 */
	protected boolean containsJawWSClientClass(final Set<String> classDependencies){
		if(classDependencies != null){
			for (String jaxwsClientClass : jawxsClassNames) {
				if(classDependencies.contains(jaxwsClientClass)){
					return true;
				}
			}
		}
		return false;
	}
}
