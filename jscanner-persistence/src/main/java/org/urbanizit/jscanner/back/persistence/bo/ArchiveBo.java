package org.urbanizit.jscanner.back.persistence.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.HashCodeBuilder;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="ARCHIVE")
public abstract class ArchiveBo implements EntityItf<Long> {

	/** SerialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ARCHIVE_ID", nullable = false, length = 32)
	@GeneratedValue
	private Long id;

	/** The name */
	@Column(name="NAME")
	private String name;

	/** The version */
	@Column(name="VERSION")
	private Integer version;

	/** The file checksum **/
	@Column(name="CHECKSUM", unique=true, nullable=true)
	private String checksum;

	/** The archive manifest **/
	@OneToOne(mappedBy="archive", cascade={CascadeType.ALL})
	private ManifestBo manifest;

	/** Signing information */
	@OneToMany(mappedBy="archive", cascade={CascadeType.ALL})
	private Set<SignEntryBo> signEntries;

	/** Class files **/
	@OneToMany(mappedBy="archive", cascade={CascadeType.ALL})
	private Set<ClassFileBo> classFiles;

	/** Class files **/
	@Column(name="REGISTRATION_DATE", nullable=false)
	private Date registrationDate;
	
	/** Class files **/
	@Column(name="IS_COMPAGNY_FILE", nullable=true)
	private Boolean compagnyFile;	
	
	/** Is Archive file **/
	@Column(name="IS_WS_ARTIFACT", nullable=true)
	private boolean wsArtifact;	
	
	@Column(name="OWNER_GROUP", nullable=true, updatable=true)
	private String ownerGroup;;
		
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	
	public boolean isWsArtifact() {
		return wsArtifact;
	}

	public void setWsArtifact(boolean wsArtifact) {
		this.wsArtifact = wsArtifact;
	}

	public Boolean getCompagnyFile() {
		return compagnyFile;
	}

	public void setCompagnyFile(Boolean compagnyFile) {
		this.compagnyFile = compagnyFile;
	}

	public String getOwnerGroup() {
		return ownerGroup;
	}

	public void setOwnerGroup(String ownerGroup) {
		this.ownerGroup = ownerGroup;
	}

	public ManifestBo getManifest() {
		return manifest;
	}
	
	public void setManifest(ManifestBo manifest) {
		if(manifest != null){
			manifest.setArchive(this);
		}
		this.manifest = manifest;
	}

	public Set<SignEntryBo> getSignEntries() {
		return signEntries;
	}

	public void setSignEntries(Set<SignEntryBo> signEntries) {
		if(signEntries != null){
			for (SignEntryBo signEntry : signEntries) {
				signEntry.setArchive(this);	
			}
		}
		this.signEntries = signEntries;
	}

	public Set<ClassFileBo> getClassFiles() {
		return classFiles;
	}

	public void setClassFiles(Set<ClassFileBo> classFiles) {
		if(classFiles != null){
			for (ClassFileBo classFile : classFiles) {
				classFile.setArchive(this);
			}
		}
		this.classFiles = classFiles;
	}
	
	
	public void addClassFiles(ClassFileBo classFile) {
		if(this.classFiles == null){
			this.classFiles = new HashSet<ClassFileBo>();
		}	
		classFile.setArchive(this);		
		this.classFiles.add(classFile);
	}
		
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();		
		hcb.append(getChecksum());
		hcb.append(getId());
		return hcb.toHashCode();
	}	
}
