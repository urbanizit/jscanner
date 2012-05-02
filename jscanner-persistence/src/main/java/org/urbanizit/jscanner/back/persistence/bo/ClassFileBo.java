package org.urbanizit.jscanner.back.persistence.bo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;


@Entity
@Table(	name = "CLASS_FILE")
public class ClassFileBo implements EntityItf<Long>  {

	/**	The serial version UID **/
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name = "CLASS_FILE_ID", nullable = false, length = 32)
	private Long id;
	
	/**	file checksum  **/
	@Column(name="CHECKSUM")
	private String checksum;
	
	/** is Interface class file **/
	@Column(name = "IS_INTERFACE")
	private Boolean isInterface;	
	
	/** is Enum class file **/
	@Column(name = "IS_ENUM")
	private Boolean isEnum;	
	
	/** Java Class Version **/
	@Column(name = "CLASS_VERSION")
	private Integer classVersion;
	
	/** Java Class Serial Version **/
	@Column(name = "CLASS_SERIAL_VERSION_UID")
	private Long classSerialVersionUID;
	
	/** Class name **/
	@Column(name = "SIMPLE_CLASS_NAME", nullable=false)
	private String  simpleClassName;
	
	/** Package name **/
	@ManyToOne
	@JoinColumn(name = "PACKAGE_NAME_ID", nullable=true)
	private PackageNameBo packageName;
	
	/** Class name **/
	@ManyToOne
	@JoinColumn(name= "CLASS_NAME_ID", nullable=false)
	private ClassNameBo className;
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Set<PackageNameBo> packageDependencies;
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Set<ClassNameBo> classDependencies;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="classFile")
	private Set<MethodBo> methods;	
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="classFile")
	private Set<MethodCallBo> methodCalls;	

	@ManyToOne
	@JoinColumn(name = "LOCAL_EJB_INTERFACE_ID", nullable=true)
	private ClassNameBo localEJBInterface;
	
	@ManyToOne
	@JoinColumn(name = "REMOTE_EJB_INTERFACE_ID", nullable=true)
	private ClassNameBo remoteEJBInterface;
		
	/** Archive class **/
	@ManyToOne 
	@JoinColumn(name="ARCHIVE_ID", nullable=false)
	private ArchiveBo archive;
	
	public ClassFileBo(){}
	
	public ClassFileBo(Long id){
		this.id = id;
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

	public Integer getClassVersion() {
		return classVersion;
	}

	public void setClassVersion(Integer classVersion) {
		this.classVersion = classVersion;
	}
	
	public PackageNameBo getPackageName() {
		return packageName;
	}

	public void setPackageName(PackageNameBo packageName) {
		this.packageName = packageName;
	}

	public ArchiveBo getArchive() {
		return archive;
	}

	public void setArchive(ArchiveBo archive) {
		this.archive = archive;
	}
	
	public Set<PackageNameBo> getPackageDependencies() {
		return packageDependencies;
	}

	public void setPackageDependencies(Set<PackageNameBo> packageDependencies) {
		this.packageDependencies = packageDependencies;
	}
	
	public void addPackageDependency(PackageNameBo packageName) {
		if(this.packageDependencies == null){
			this.packageDependencies = new HashSet<PackageNameBo>();
		}
		this.packageDependencies.add(packageName);
	}
		
	public String getSimpleClassName() {
		return simpleClassName;
	}

	public void setSimpleClassName(String simpleClassName) {
		this.simpleClassName = simpleClassName;
	}

	public ClassNameBo getClassName() {
		return className;
	}

	public void setClassName(ClassNameBo className) {
		this.className = className;
	}

	public Set<ClassNameBo> getClassDependencies() {
		return classDependencies;
	}

	public void setClassDependencies(Set<ClassNameBo> classDependencies) {
		this.classDependencies = classDependencies;
	}
	
	public void addClassDependency(ClassNameBo className) {
		if(this.classDependencies == null){
			this.classDependencies = new HashSet<ClassNameBo>();
		}
		this.classDependencies.add(className);
	}
	
	public Long getClassSerialVersionUID() {
		return classSerialVersionUID;
	}

	public void setClassSerialVersionUID(Long classSerialVersionUID) {
		this.classSerialVersionUID = classSerialVersionUID;
	}
	
	public Boolean getIsInterface() {
		return isInterface;
	}

	public void setIsInterface(Boolean isInterface) {
		this.isInterface = isInterface;
	}

	public Set<MethodBo> getMethods() {
		return methods;
	}

	public void setMethods(Set<MethodBo> methods) {
		this.methods = methods;
	}
	
	public void addMethod(MethodBo method) {
		if(this.methods == null){
			this.methods = new HashSet<MethodBo>();
		}
		method.setClassFile(this);
		this.methods.add(method);
	}
	
	
	public Boolean getIsEnum() {
		return isEnum;
	}

	public void setIsEnum(Boolean isEnum) {
		this.isEnum = isEnum;
	}
	
	public Set<MethodCallBo> getMethodCalls() {
		return methodCalls;
	}

	public void setMethodCalls(Set<MethodCallBo> methodCalls) {
		this.methodCalls = methodCalls;
	}
	
	public void addMethodCall(MethodCallBo methodCall) {
		if(this.methodCalls == null){
			this.methodCalls = new HashSet<MethodCallBo>();
		}
		methodCall.setClassFile(this);
		this.methodCalls.add(methodCall);
	}
	
	public ClassNameBo getLocalEJBInterface() {
		return localEJBInterface;
	}

	public void setLocalEJBInterface(ClassNameBo localEJBInterface) {
		this.localEJBInterface = localEJBInterface;
	}

	public ClassNameBo getRemoteEJBInterface() {
		return remoteEJBInterface;
	}

	public void setRemoteEJBInterface(ClassNameBo remoteEJBInterface) {
		this.remoteEJBInterface = remoteEJBInterface;
	}

	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);
		tsb.append("checksum", checksum);
		tsb.append("classVersion", classVersion);
		//tsb.append("className", className);
		tsb.append("packageName", packageName);
		return tsb.toString();
	}
}


