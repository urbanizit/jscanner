package org.urbanizit.jscanner.back.persistence.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


@Entity
@Table(	name = "METHOD_SIGNATURE")
@NamedQueries(value={
		@NamedQuery(name="FIND_METHOD_SIGNATURE", query="select ms from MethodSignatureBo ms where hashCode = :hashCode and methodSignature = :methodSignature"),
		@NamedQuery(name="FIND_METHOD_SIGNATURE_IN", query="select ms from MethodSignatureBo ms where  methodSignature in (:methodSignatures)")
})
public class MethodSignatureBo implements EntityItf<Long>  {

	/**	The serial version UID **/
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name = "METHOD_SIGNATURE_ID", nullable = false)
	private Long id;
	
	/** Method signature **/
	@Column(name = "METHOD_SIGNATURE", nullable = false, unique=true, length= 2048)	
	private String methodSignature;
	
	/** Method human readable signature **/
	@Column(name = "READABLE_SIGNATURE", nullable=false, length= 2048)
	private String readableSignature;
	
	@Column(name = "HASHCODE", nullable = false, unique=false)	
	private Integer hashCode ;

	public MethodSignatureBo(){}
	

	public MethodSignatureBo(String methodSignature, String readableSignature){
		this.methodSignature = methodSignature;
		this.readableSignature = readableSignature;
		this.hashCode = methodSignature == null ? 0 : methodSignature.hashCode();
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

	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);
		tsb.append("methodSignature", methodSignature);
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


