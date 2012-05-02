package org.urbanizit.jscanner.back.common.extractor;

import java.util.Collection;

import org.urbanizit.jscanner.transfert.ClassFile;


public class ClassFilePackageExtractor implements DataExtractor<ClassFile, String> {

	@Override
	public void extractData(ClassFile classFile, Collection<String> accumulator) {

		if(classFile != null){
			accumulator.add(classFile.getPackageName());
			
			if(classFile.getPackageDependencies()!= null){
				accumulator.addAll(classFile.getPackageDependencies());
			}	
		}		
	}
}
