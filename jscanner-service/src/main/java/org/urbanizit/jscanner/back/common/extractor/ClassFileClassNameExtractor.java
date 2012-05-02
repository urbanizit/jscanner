package org.urbanizit.jscanner.back.common.extractor;

import java.util.Collection;

import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.MethodCall;



public class ClassFileClassNameExtractor implements DataExtractor<ClassFile, String> {

	@Override
	public void extractData(ClassFile classFile, Collection<String> accumulator) {

		if(classFile != null){	
			accumulator.add(classFile.getCanonicalName());
			
			if(classFile.getClassDependencies() != null){
				accumulator.addAll(classFile.getClassDependencies());
			}				
			if(classFile.getMethodCalls() != null){
				for (MethodCall methodCall : classFile.getMethodCalls()) {
					accumulator.add(methodCall.getClassName());
				}
			}
		}
	}
}
