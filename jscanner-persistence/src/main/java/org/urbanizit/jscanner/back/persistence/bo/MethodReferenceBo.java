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
@Table(	name = "METHOD_REFERENCE")
@NamedQueries(value={
		@NamedQuery(name="FIND_METHOD_REFERENCES_BY_SIGNATURE_AND_CLASS_NAME", query="select mr from MethodReferenceBo mr where signature.id = :signatureId and className.id = :classNameId"),
		@NamedQuery(name="FIND_METHOD_REFERENCES_BY_HASHCODE_IN", query="select mr from MethodReferenceBo mr where hashcode in (:hashcodes)")
})
public class MethodReferenceBo implements EntityItf<Long>  {

	/**	The serial version UID **/
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name = "METHOD_REFERENCE_ID", nullable = false)
	private Long id;
		/** method signature name **/
	@ManyToOne
	@JoinColumn(name= "METHOD_SIGNATURE_ID", nullable=false)
	private MethodSignatureBo signature;
	
	/** Class providing method **/
	@ManyToOne @JoinColumn(name = "CLASS_NAME_ID", nullable=false)
	private ClassNameBo className;
	
	/** Reference HashCode **/	
	@Column(name = "HASHCODE", nullable = false, unique=false)	
	private String hashCode ;
	
	public MethodReferenceBo(){}
	
	public MethodReferenceBo(ClassNameBo className, MethodSignatureBo signature, String hashCode){
		this.className = className;
		this.signature = signature;
		this.hashCode = hashCode;
	}

	@Override
	public Long getId() {		
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;		
	}
	
	public MethodSignatureBo getSignature() {
		return signature;
	}

	public void setSignature(MethodSignatureBo signature) {
		this.signature = signature;
	}

	public ClassNameBo getClassName() {
		return className;
	}

	public void setClassName(ClassNameBo className) {
		this.className = className;
	}
	
	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);
		return tsb.toString();
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(id);		
		return hcb.toHashCode();
	}
}


