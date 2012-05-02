package org.urbanizit.jscanner.back.persistence.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Manifest entry value
 * @author ldassonville
 */
@Entity
@Table(name="MANIFEST_ENTRY")
public class ManifestEntryBo  implements EntityItf<Long>  {

	/** SerialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "MANIFEST_ENTRY_ID", nullable = false, length = 32)
	private Long id;

	/** Manifest key */
	@Column(name = "KEY")
	private String key;
	
	/** Manifest value */
	@Column(name = "VALUE")
	private String value;
	
	/** Manifest */
	@ManyToOne @JoinColumn(name = "MANIFEST_ID", nullable=false)
	private ManifestBo manifest;
		
	public ManifestEntryBo(){}
	
	public ManifestEntryBo(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	
		
	public ManifestBo getManifest() {
		return manifest;
	}

	public void setManifest(ManifestBo manifest) {
		this.manifest = manifest;
	}

	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);
		tsb.append("key", key);
		tsb.append("value", value);
		return tsb.toString();
	}
}
