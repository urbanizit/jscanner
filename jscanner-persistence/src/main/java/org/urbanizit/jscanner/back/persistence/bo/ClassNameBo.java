package org.urbanizit.jscanner.back.persistence.bo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


@Entity
@Table(	name = "CLASS_NAME")
@NamedQueries(value={
		@NamedQuery(name="FIND_CLASS_NAME", query="select c from ClassNameBo c where  c.hashCode = :hashCode and c.className = :className"),
		@NamedQuery(name="FIND_CLASS_NAME_IN", query="select c from ClassNameBo c where  c.className in (:classNames)")
})
public class ClassNameBo implements EntityItf<Long>  {

	/**	The serial version UID **/
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name = "CLASS_NAME_ID", nullable = false)
	private Long id;
	
	@Column(name = "CLASS_NAME", nullable = false, unique=true)	
	private String className;
	
	@Column(name = "HASHCODE", nullable = false, unique=false)	
	private Integer hashCode ;
	
	@ManyToMany(mappedBy="classDependencies")
	private Set<ClassFileBo> classFileDependencies;
	
	@OneToMany(cascade={}, mappedBy="className")
	private Set<ClassFileBo> classFiles;	
	
	
	public ClassNameBo(){}
	

	public ClassNameBo(String className){
		this.className = className;
		this.hashCode = className == null ? 0 : className.hashCode();
	}

	@Override
	public Long getId() {		
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;		
	}
		
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	

	public Set<ClassFileBo> getClassFileDependencies() {
		return classFileDependencies;
	}


	public void setClassFileDependencies(Set<ClassFileBo> classFileDependencies) {
		this.classFileDependencies = classFileDependencies;
	}

	

	public Set<ClassFileBo> getClassFiles() {
		return classFiles;
	}


	public void setClassFiles(Set<ClassFileBo> classFiles) {
		this.classFiles = classFiles;
	}


	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);
		tsb.append("className", className);
		tsb.append("hashCode", hashCode);
		return tsb.toString();
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(className);		
		return hcb.toHashCode();
	}
}


