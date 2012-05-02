package org.urbanizit.jscanner.transfert;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Method implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String methodName;
	private String methodSignature;
	private String methodReadableSignature;
	private String className;
	
	public Method(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodSignature() {
		return methodSignature;
	}

	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature;
	}

	public String getMethodReadableSignature() {
		return methodReadableSignature;
	}

	public void setMethodReadableSignature(String methodReadableSignature) {
		this.methodReadableSignature = methodReadableSignature;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
	@Override
	public String toString() {
		return  new ToStringBuilder(this)
		.append("methodReadableSignature", methodReadableSignature)
		.toString();
	}
}
