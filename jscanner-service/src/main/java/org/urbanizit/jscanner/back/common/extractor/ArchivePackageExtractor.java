package org.urbanizit.jscanner.back.common.extractor;

import org.urbanizit.jscanner.transfert.ClassFile;


public class ArchivePackageExtractor extends AbstractArchiveDataExtractor<String> {

	public ArchivePackageExtractor(){};
	
	public ArchivePackageExtractor(boolean isRecursive){
		isRecursiveExtraction = isRecursive;
	}
	
	protected DataExtractor<ClassFile, String> getExtractor(){
		return new ClassFilePackageExtractor();
	}
}
