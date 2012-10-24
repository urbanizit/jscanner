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
@Table(	name = "METHOD")
public class MethodBo implements EntityItf<Long>  {

	/**	The serial version UID **/
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name = "METHOD_ID", nullable = false, length = 32)
	private Long id;

	/** Method name **/
	@Column(name = "IS_CONSTRUCTOR", nullable=false)
	private boolean constructor;
	
	/** Method name **/
	@Column(name = "NAME", nullable=false)
	private String methodName;

	/** method signature name **/
	@ManyToOne
	@JoinColumn(name= "METHOD_SIGNATURE_ID", nullable=false)
	private MethodSignatureBo signature;

	/** Origin class File **/
	@ManyToOne @JoinColumn(name = "CLASS_FILE_ID", nullable=false)
	private ClassFileBo classFile;

	public MethodBo(){}
	
	public MethodBo(Long id){
		this.id = id;
	}
	
	public MethodBo(Long id, String methodName){
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
	
	public boolean isConstructor() {
		return constructor;
	}

	public void setConstructor(boolean constructor) {
		this.constructor = constructor;
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


