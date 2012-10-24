package org.urbanizit.jscanner.back.common.extractor;

import org.urbanizit.jscanner.back.common.resolver.pojo.MethodSignatureIdentifier;
import org.urbanizit.jscanner.transfert.ClassFile;


public class ArchiveMethodSignatureExtractor extends AbstractArchiveDataExtractor<MethodSignatureIdentifier> {

	public ArchiveMethodSignatureExtractor(){};
	
	public ArchiveMethodSignatureExtractor(boolean isRecursive){
		isRecursiveExtraction = isRecursive;
	}
	
	protected DataExtractor<ClassFile, MethodSignatureIdentifier> getExtractor(){
		return new ClassFileMethodSignatureExtractor();
	}
}
