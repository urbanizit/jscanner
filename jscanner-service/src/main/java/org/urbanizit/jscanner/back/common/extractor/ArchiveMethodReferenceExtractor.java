package org.urbanizit.jscanner.back.common.extractor;

import org.urbanizit.jscanner.back.common.resolver.pojo.MethodSignatureIdentifier;
import org.urbanizit.jscanner.transfert.ClassFile;


public class ArchiveMethodReferenceExtractor extends AbstractArchiveDataExtractor<MethodSignatureIdentifier> {
	
	public ArchiveMethodReferenceExtractor(){};
	
	public ArchiveMethodReferenceExtractor(boolean isRecursive){
		isRecursiveExtraction = isRecursive;
	}
	
	protected DataExtractor<ClassFile, MethodSignatureIdentifier> getExtractor(){		
		return new ClassFileMethodReferenceExtractor();
	}
}
