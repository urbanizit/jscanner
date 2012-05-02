package org.urbanizit.jscanner.back.common.extractor;

import org.urbanizit.jscanner.transfert.ClassFile;


public class ArchiveMethodSignatureExtractor extends AbstractArchiveDataExtractor<String> {

	public ArchiveMethodSignatureExtractor(){};
	
	public ArchiveMethodSignatureExtractor(boolean isRecursive){
		isRecursiveExtraction = isRecursive;
	}
	
	protected DataExtractor<ClassFile, String> getExtractor(){
		return new ClassFileMethodSignatureExtractor();
	}
}
