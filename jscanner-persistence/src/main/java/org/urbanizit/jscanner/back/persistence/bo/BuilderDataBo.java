package org.urbanizit.jscanner.back.persistence.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.HashCodeBuilder;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="BUILDER_DATA")
public class BuilderDataBo implements EntityItf<Long> {

	/** SerialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Id	@GeneratedValue
	@Column(name = "BUILDER_DATA_ID", nullable = false, length = 32)
	private Long id;

	@Column(name="BUILDER_TYPE")
	private String builderType;
	
	/** Archive **/
	@OneToOne
	private ArchiveBo archive;

	/** The group Id */
	@Column(name="GROUP_ID")
	private String groupId;

	/** The artifact Id */
	@Column(name="ARTIFACT_ID")
	private String artifactId;

	/** The version */
	@Column(name="VERSION")
	private String version;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArchiveBo getArchive() {
		return archive;
	}

	public void setArchive(ArchiveBo archive) {
		this.archive = archive;
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
	
	public String getBuilderType() {
		return builderType;
	}

	public void setBuilderType(String builderType) {
		this.builderType = builderType;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();	
		
		hcb.append(getId());
		hcb.append(getGroupId());
		hcb.append(getArtifactId());
		hcb.append(getVersion());
		return hcb.toHashCode();
	}	
}
