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
 * Sign entry value
 * @author ldassonville
 */
@Entity
@Table(name="SIGN")
public class SignEntryBo  implements EntityItf<Long>  {

	/** SerialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SIGN_ENTRY_ID", nullable = false, length = 32)
	@GeneratedValue
	private Long id;
	
	/** Sign value **/
	private String value;
	
	/** Archive sign entry **/
	@ManyToOne 
	@JoinColumn(name="ARCHIVE_ID", nullable=false)
	private ArchiveBo archive;
		
	public SignEntryBo(){}
	
	public SignEntryBo(Long id){
		this.id = id;
	}
	
	public SignEntryBo(Long id, String value){
		this.value = value;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public ArchiveBo getArchive() {
		return archive;
	}

	public void setArchive(ArchiveBo archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", id);
		tsb.append("value", value);
		return tsb.toString();
	}
}
