package org.urbanizit.jscanner.transfert;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.urbanizit.jscanner.transfert.enums.LinkType;


public class Link implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long archiveId1;
	private Long archiveId2;
	private LinkType type;

	public Link(){}
	
	public Link(Long archiveId1, Long archiveId2, LinkType depend){
		this.archiveId1 = archiveId1;
		this.archiveId2 = archiveId2;
		this.type = depend;		
	}
		
	public Long getArchiveId1() {
		return archiveId1;
	}
	public void setArchiveId1(Long archiveId1) {
		this.archiveId1 = archiveId1;
	}
	public Long getArchiveId2() {
		return archiveId2;
	}
	public void setArchiveId2(Long archiveId2) {
		this.archiveId2 = archiveId2;
	}
	public LinkType getType() {
		return type;
	}
	public void setType(LinkType type) {
		this.type = type;
	}	
	@Override
	public String toString() {
		return new ToStringBuilder(this).
		append("archiveId1",archiveId1).
		append(type).
		append("archiveId2",archiveId2).
		toString();
	}
}
