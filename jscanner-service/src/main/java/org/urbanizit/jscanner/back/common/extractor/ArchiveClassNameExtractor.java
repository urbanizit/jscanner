package org.urbanizit.jscanner.back.common.extractor;

import org.urbanizit.jscanner.transfert.ClassFile;


public class ArchiveClassNameExtractor extends AbstractArchiveDataExtractor<String> {

	public ArchiveClassNameExtractor(){};
	
	public ArchiveClassNameExtractor(boolean isRecursive){
		isRecursiveExtraction = isRecursive;
	}
	
	protected DataExtractor<ClassFile, String> getExtractor(){
		return new ClassFileClassNameExtractor();
	}


}
