package org.urbanizit.jscanner.transfert;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ClassFile implements Serializable {

	private static final long serialVersionUID = 7250244678157929200L;
	
    public ClassFile(){}
    
    private Long id;
    private String className;
    private Boolean isInterface;
    private Boolean isEnum;
    private Integer classVersion;
    private Long classSerialVersionUID;
    private String packageName;
	private String checksum;
	private String canonicalName;
	private Set<String> packageDependencies;
	private Set<String> classDependencies;
	private List<Method> methods;
	private List<MethodCall> methodCalls;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassVersion() {
		return classVersion;
	}
	public void setClassVersion(Integer classVersion) {
		this.classVersion = classVersion;
	}
	
	public Long getClassSerialVersionUID() {
		return classSerialVersionUID;
	}
	public void setClassSerialVersionUID(Long classSerialVersionUID) {
		this.classSerialVersionUID = classSerialVersionUID;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageName() {
		return packageName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getCanonicalName() {
		return canonicalName;
	}
	public void setCanonicalName(String canonicalName) {
		this.canonicalName = canonicalName;
	}
	public Set<String> getPackageDependencies() {
		return packageDependencies;
	}
	public void setPackageDependencies(Set<String> packageDependencies) {
		this.packageDependencies = packageDependencies;
	}
	public Set<String> getClassDependencies() {
		return classDependencies;
	}
	public void setClassDependencies(Set<String> classDependencies) {
		this.classDependencies = classDependencies;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	public Boolean getIsInterface() {
		return isInterface;
	}
	public void setIsInterface(Boolean isInterface) {
		this.isInterface = isInterface;
	}
	public Boolean getIsEnum() {
		return isEnum;
	}
	public void setIsEnum(Boolean isEnum) {
		this.isEnum = isEnum;
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	public List<MethodCall> getMethodCalls() {
		return methodCalls;
	}
	public void setMethodCalls(List<MethodCall> methodCalls) {
		this.methodCalls = methodCalls;
	}
	@Override
	public String toString() {
		return  new ToStringBuilder(this)
		.append("className", className)
		.toString();
	}
}
