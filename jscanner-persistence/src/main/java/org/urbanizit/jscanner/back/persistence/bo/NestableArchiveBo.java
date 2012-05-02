package org.urbanizit.jscanner.back.persistence.bo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * Nestable archive.
 * 
 * @author ldassonville
 */
@Entity
public abstract class NestableArchiveBo extends ArchiveBo {

	/** Serial UID **/
	private static final long serialVersionUID = 1L;
	
	/** Sub-archives */
	@ManyToMany(cascade={CascadeType.PERSIST})
	private Set<ArchiveBo> subArchives;

	public Set<ArchiveBo> getSubArchives() {
		return subArchives;
	}

	public void setSubArchives(Set<ArchiveBo> subArchives) {
		this.subArchives = subArchives;
	}
		
	public void addSubArchive(ArchiveBo subArchive) {
		if(subArchives == null){
			subArchives = new HashSet<ArchiveBo>();
		}		
		subArchives.add(subArchive);
	}
	
	public void addAllSubArchives(Collection<ArchiveBo> subArchives) {
		if(this.subArchives == null){
			this.subArchives = new HashSet<ArchiveBo>();
		}		
		this.subArchives.addAll(subArchives);
	}
}
