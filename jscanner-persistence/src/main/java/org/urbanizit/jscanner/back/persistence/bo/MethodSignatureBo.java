package org.urbanizit.jscanner.back.persistence.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


@Entity
@Table(	name = "METHOD_SIGNATURE")
@NamedQueries(value={
		@NamedQuery(name="FIND_METHOD_SIGNATURE", query="select ms from MethodSignatureBo ms where ms.hashCode = :hashCode and ms.methodSignature = :methodSignature"),
		@NamedQuery(name="FIND_METHOD_SIGNATURE_IN", query="select ms from MethodSignatureBo ms where  ms.qualifiedReadableSignature in (:methodSignatures)")
})
public class MethodSignatureBo implements EntityItf<Long>  {

	/**	The serial version UID **/
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name = "METHOD_SIGNATURE_ID", nullable = false)
	private Long id;
	
	/** Method signature **/
	@Column(name = "METHOD_SIGNATURE", nullable = false, unique=false,  length= 2048)	
	private String methodSignature;
	
	/** Method human readable signature **/
	@Column(name = "READABLE_SIGNATURE", nullable=false, length= 8000)
	private String readableSignature;
	
	/** Method human readable signature **/
	@Column(name = "QUALIFIED_READABLE_SIGNATURE", nullable=false, length= 8000, unique=true)
	private String qualifiedReadableSignature;
	
	/** Class providing method **/
	@ManyToOne @JoinColumn(name = "CLASS_NAME_ID", nullable=false)
	private ClassNameBo className;
	
	@Column(name = "HASHCODE", nullable = false, unique=false)	
	private String hashCode ;

	public MethodSignatureBo(){}
	

	public MethodSignatureBo(String methodSignature, String readableSignature, ClassNameBo classNameBo){
		this.methodSignature = methodSignature;
		this.readableSignature = readableSignature;
		this.className = classNameBo;
		this.qualifiedReadableSignature = className.getClassName() +"."+ readableSignature;
		//TODO correction to set good hashcode		
		this.hashCode =methodSignature.hashCode()+"_"+classNameBo.getClassName().hashCode();
	}

	@Override
	public Long getId() {		
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;		
	}
		
	public String getMethodSignature() {
		return methodSignature;
	}

	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature;
	}
	
	public String getReadableSignature() {
		return readableSignature;
	}

	public void setReadableSignature(String readableSignature) {
		this.readableSignature = readableSignature;
	}
	
	public ClassNameBo getClassName() {
		return className;
	}

	public void setClassName(ClassNameBo className) {
		this.className = className;
	}


	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);
		tsb.append("methodSignature", methodSignature);
		tsb.append("qualifiedReadableSignature", qualifiedReadableSignature);		
		tsb.append("hashCode", hashCode);
		return tsb.toString();
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(methodSignature);		
		return hcb.toHashCode();
	}
}


