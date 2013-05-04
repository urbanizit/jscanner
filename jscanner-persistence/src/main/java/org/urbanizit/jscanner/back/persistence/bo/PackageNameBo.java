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
@Table(	name = "PACKAGE_NAME")
@NamedQueries(value={
		@NamedQuery(name="FIND_PACKAGE_NAME", query="select p from PackageNameBo p where p.packageName=:packageName"),
		@NamedQuery(name="FIND_PACKAGE_NAME_IN", query="select p from PackageNameBo p where p.packageName in (:packageNames)")
}) 
public class PackageNameBo implements EntityItf<Long>  {

	/**	The serial version UID **/
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name = "PACKAGE_ID", nullable = false)
	private Long id;
	
	@Column(name = "PACKAGE_NAME", nullable = false, unique=true)	
	private String packageName;
		
	public PackageNameBo(){}

	public PackageNameBo(String packageName){
		this.packageName = packageName;
	}

	@Override
	public Long getId() {		
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;		
	}
		
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);
		tsb.append("packageName", packageName);
		return tsb.toString();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(packageName);		
		return hcb.toHashCode();
	}
}

