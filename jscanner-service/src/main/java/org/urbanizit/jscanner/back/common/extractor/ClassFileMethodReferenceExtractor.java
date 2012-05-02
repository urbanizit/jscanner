package org.urbanizit.jscanner.back.common.extractor;

import java.util.Collection;

import org.urbanizit.jscanner.back.common.resolver.pojo.MethodReferenceIdentifier;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.MethodCall;



public class ClassFileMethodReferenceExtractor implements DataExtractor<ClassFile, MethodReferenceIdentifier> {

	@Override
	public void extractData(ClassFile classFile, Collection<MethodReferenceIdentifier> accumulator) {

		if(classFile != null){
			if(classFile.getMethods() != null){
				for (Method method : classFile.getMethods()) {					
					MethodReferenceIdentifier methodReference = new MethodReferenceIdentifier();
					methodReference.setClassName(classFile.getCanonicalName());
					methodReference.setMethodSignature(method.getMethodSignature());
					accumulator.add(methodReference);
				}
			}	
		
			if(classFile.getMethodCalls() != null){
				for (MethodCall methodCall : classFile.getMethodCalls()) {
					MethodReferenceIdentifier methodReference = new MethodReferenceIdentifier();
					methodReference.setClassName(methodCall.getClassName());
					methodReference.setMethodSignature(methodCall.getMethodSignature());
					accumulator.add(methodReference);
				}
			}
		}
	}
}
