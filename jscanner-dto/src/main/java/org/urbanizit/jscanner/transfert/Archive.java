package org.urbanizit.jscanner.transfert;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Archive implements Serializable {

	private static final long serialVersionUID = 7250244678157929200L;
	
	protected Long id;
	protected String name;
	protected String checksum;    
	protected String ownerGroup;
	protected Boolean compagnyFile;
	protected Date registrationDate;
	protected BuilderData builderData;
	protected Location location;	
	protected List<String> signs;
	protected List<ClassFile> classFiles;
	protected boolean wsArtifact;	
      
    public Archive(){}
       
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Archive(String name, Location location) {
		this.name = name;
	}
    
    public Archive(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	
	public List<ClassFile> getClassFiles() {
		return classFiles;
	}
	public void setClassFiles(List<ClassFile> classFiles) {
		this.classFiles = classFiles;
	}

	public void setSigns(List<String> signs) {
		this.signs = signs;
	}
	public List<String> getSigns() {
		return signs;
	}
		
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
		
	public String getOwnerGroup() {
		return ownerGroup;
	}

	public void setOwnerGroup(String ownerGroup) {
		this.ownerGroup = ownerGroup;
	}
	
	public Boolean getCompagnyFile() {
		return compagnyFile;
	}

	public void setCompagnyFile(Boolean compagnyFile) {
		this.compagnyFile = compagnyFile;
	}

	public boolean isWsArtifact() {
		return wsArtifact;
	}

	public void setWsArtifact(boolean wsArtifact) {
		this.wsArtifact = wsArtifact;
	}

	public BuilderData getBuilderData() {
		return builderData;
	}

	public void setBuilderData(BuilderData builderData) {
		this.builderData = builderData;
	}

	@Override
	public int hashCode() {
		return  new HashCodeBuilder()
		.append(id)
		.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Archive))return false;
		
		Archive otherArchive = (Archive) obj;		
		return new EqualsBuilder().append(otherArchive.getId(), id).isEquals();
	}

	@Override
	public String toString() {
		return  new ToStringBuilder(this)
		.append("name", name)
		.toString();
	}

}
