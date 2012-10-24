package org.urbanizit.jscanner.back.common.resolver.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class MethodSignatureIdentifier{
	
	private String className;
	private String methodSignature;
	
	public MethodSignatureIdentifier(){}
	
	public MethodSignatureIdentifier(String className, String methodSignature){
		this.className = className;
		this.methodSignature = methodSignature;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodSignature() {
		return methodSignature;
	}
	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature;
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(className);
		hcb.append(methodSignature);
		return hcb.toHashCode();
	}


	public String toStringHashCode(){		
		return Integer.toHexString(className.hashCode())+ "_" + Integer.toHexString(methodSignature.hashCode());	
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof MethodSignatureIdentifier)){
			return false;
		}
		
		MethodSignatureIdentifier other = (MethodSignatureIdentifier)obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(className, other.className);
		eb.append(methodSignature, other.methodSignature);
		return eb.isEquals();
	}
	
	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("className", className);
		tsb.append("methodSignature", methodSignature);
		return tsb.toString();
	}
	
}