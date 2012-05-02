package org.urbanizit.jscanner.back.persistence.bo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * File Manifest entity
 * @author ldassonville
 */
@Entity
@Table(name="MANIFEST")
public class ManifestBo implements EntityItf<Long>  {

	/** SerialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id	@GeneratedValue
	@Column(name = "MANIFEST_ID", nullable = false, length = 32)
	private Long id;

	/** Archive **/
	@OneToOne
	private ArchiveBo archive;
	
	/** Manifest */
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="manifest")
	private Set<ManifestEntryBo> entries;

	public ManifestBo(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ManifestEntryBo> getEntries() {
		return entries;
	}

	public void setEntries(Set<ManifestEntryBo> entries) {
		if(entries != null){
			for (ManifestEntryBo manifestEntry : entries) {
				manifestEntry.setManifest(this);
			}
		}
		this.entries = entries;
	}
		
	public ArchiveBo getArchive() {
		return archive;
	}

	public void setArchive(ArchiveBo archive) {
		this.archive = archive;
	}

	public String toString() {		
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);	
		return tsb.toString();
	}	
}
