package org.urbanizit.jscanner.transfert;

import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class NestableArchive extends Archive {

	private static final long serialVersionUID = 7250244678157929200L;
    private List<Archive> subArchives;
	
    public NestableArchive(){}
    
	public NestableArchive(String name, Location location){
		super(name, location);
	}
    
	public void setSubArchives(List<Archive> subArchives) {
		this.subArchives = subArchives;
	}
	public List<Archive> getSubArchives() {
		return subArchives;
	}	
	
	
	@Override
	public int hashCode() {
		return  new HashCodeBuilder()
		.append(id)
		.toHashCode();
	}

	
	@Override
	public String toString() {
		return  new ToStringBuilder(this)
		.toString();
	}

}
