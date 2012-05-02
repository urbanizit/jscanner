package org.urbanizit.jscanner.back.persistence.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;


@Entity
@Table(	name = "METHOD_CALL")
public class MethodCallBo implements EntityItf<Long>  {

	/**	The serial version UID **/
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name = "METHOD_CALL_ID", nullable = false, length = 32)
	private Long id;

	/** Method name **/
	@Column(name = "NAME", nullable=false)
	private String methodName;

	/** method signature name **/
	@ManyToOne
	@JoinColumn(name= "METHOD_SIGNATURE_ID", nullable=false)
	private MethodSignatureBo signature;
	
	/** Origin class File call**/
	@ManyToOne @JoinColumn(name = "CLASS_FILE_ID", nullable=false)
	private ClassFileBo classFile;
	
	/** Class providing method **/
	@ManyToOne @JoinColumn(name = "CLASS_NAME_ID", nullable=false)
	private ClassNameBo className;
		
	/** method signature name **/
	@ManyToOne
	@JoinColumn(name= "METHOD_REFERENCE_ID", nullable=false)
	private MethodReferenceBo methodReference;
	
	public MethodCallBo(){}
	
	public MethodCallBo(Long id){
		this.id = id;
	}
	
	public MethodCallBo(Long id, String methodName){
		this.id = id;
		this.methodName = methodName;
	}
		
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

	public MethodSignatureBo getSignature() {
		return signature;
	}

	public void setSignature(MethodSignatureBo methodSignature) {
		this.signature = methodSignature;
	}
	
	public ClassFileBo getClassFile() {
		return classFile;
	}

	public void setClassFile(ClassFileBo classFile) {
		this.classFile = classFile;
	}

	public ClassNameBo getClassName() {
		return className;
	}

	public void setClassName(ClassNameBo className) {
		this.className = className;
	}
	
	public MethodReferenceBo getMethodReference() {
		return methodReference;
	}

	public void setMethodReference(MethodReferenceBo methodReference) {
		this.methodReference = methodReference;
	}

	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);
		tsb.append("methodName", methodName);
		tsb.append("signature", signature);
		return tsb.toString();
	}
}


