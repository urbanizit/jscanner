package org.urbanizit.jscanner.back.common.extractor;

import java.util.Collection;

import org.urbanizit.jscanner.back.common.resolver.pojo.MethodSignatureIdentifier;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.MethodCall;



public class ClassFileMethodSignatureExtractor implements DataExtractor<ClassFile, MethodSignatureIdentifier> {

	@Override
	public void extractData(ClassFile classFile, Collection<MethodSignatureIdentifier> accumulator) {

		if(classFile != null){
						
			if(classFile.getMethods() != null){
				for (Method method : classFile.getMethods()) {
					accumulator.add(new MethodSignatureIdentifier(classFile.getCanonicalName(), method.getMethodSignature()));
				}
			}	
			
			if(classFile.getMethodCalls() != null){
				for (MethodCall methodCall : classFile.getMethodCalls()) {
					accumulator.add(new MethodSignatureIdentifier(methodCall.getClassName(), methodCall.getMethodSignature()));
				}
			}
		}
	}
}
