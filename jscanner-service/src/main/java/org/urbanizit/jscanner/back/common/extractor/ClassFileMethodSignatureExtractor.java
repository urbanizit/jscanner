package org.urbanizit.jscanner.back.common.extractor;

import java.util.Collection;

import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.MethodCall;



public class ClassFileMethodSignatureExtractor implements DataExtractor<ClassFile, String> {

	@Override
	public void extractData(ClassFile classFile, Collection<String> accumulator) {

		if(classFile != null){
						
			if(classFile.getMethods() != null){
				for (Method method : classFile.getMethods()) {
					accumulator.add(method.getMethodSignature());
				}
			}	
			
			if(classFile.getMethodCalls() != null){
				for (MethodCall methodCall : classFile.getMethodCalls()) {
					accumulator.add(methodCall.getMethodSignature());
				}
			}
		}
	}
}
