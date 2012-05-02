package org.urbanizit.jscanner.back.common.extractor;

import org.urbanizit.jscanner.back.common.resolver.pojo.MethodReferenceIdentifier;
import org.urbanizit.jscanner.transfert.ClassFile;


public class ArchiveMethodReferenceExtractor extends AbstractArchiveDataExtractor<MethodReferenceIdentifier> {
	
	public ArchiveMethodReferenceExtractor(){};
	
	public ArchiveMethodReferenceExtractor(boolean isRecursive){
		isRecursiveExtraction = isRecursive;
	}
	
	protected DataExtractor<ClassFile, MethodReferenceIdentifier> getExtractor(){		
		return new ClassFileMethodReferenceExtractor();
	}
}
