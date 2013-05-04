package org.urbanizit.jscanner.transfert;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BuilderData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Long id;
	public String builderType;	
	public String groupId;
	public String artifactId;
	public String version;
	
	public BuilderData(){}
	
	public BuilderData(String builderType, String groupId, String artifactId,
			String version) {
		super();
		this.builderType = builderType;
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBuilderType() {
		return builderType;
	}
	public void setBuilderType(String builderType) {
		this.builderType = builderType;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return  new ToStringBuilder(this)
		.append("id", id)
		.append("builderType", builderType)
		.append("groupId", groupId)
		.append("artifactId", artifactId)
		.append("version", version)
		.toString();
	}
	
}
