package org.urbanizit.jscanner.transfert;

import java.util.List;


public class ClassDependencyReport {
	
	private ClassFile classProvider;
	private ClassFile classCustomer;
	
	private List<String> method;

	public ClassFile getClassProvider() {
		return classProvider;
	}

	public void setClassProvider(ClassFile classProvider) {
		this.classProvider = classProvider;
	}

	public ClassFile getClassCustomer() {
		return classCustomer;
	}

	public void setClassCustomer(ClassFile classCustomer) {
		this.classCustomer = classCustomer;
	}

	public List<String> getMethod() {
		return method;
	}

	public void setMethod(List<String> method) {
		this.method = method;
	}
}
