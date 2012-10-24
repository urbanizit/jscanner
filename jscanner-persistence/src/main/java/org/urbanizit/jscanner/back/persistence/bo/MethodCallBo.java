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

	/** method signature name **/
	@ManyToOne
	@JoinColumn(name= "METHOD_SIGNATURE_ID", nullable=false)
	private MethodSignatureBo signature;
	
	/** Origin class File call**/
	@ManyToOne @JoinColumn(name = "CLASS_FILE_ID", nullable=false)
	private ClassFileBo classFile;
			
	public MethodCallBo(){}
	
	public MethodCallBo(Long id){
		this.id = id;
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);
		tsb.append("signature", signature);
		return tsb.toString();
	}
}


