package org.urbanizit.jscanner.transfert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Graph implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Archive> archives;
	private List<Link> links;

    public Graph(){}
   
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Archive> getArchives() {
		return archives;
	}

	public void setArchives(List<Archive> archives) {
		this.archives = archives;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.toString();
	}

	public void addLink(Link linkDtoI) {
		if(links == null){
			links = new ArrayList<Link>();
		}
		links.add(linkDtoI);
	}
	
	public void addArchive(Archive archiveDtoI) {
		if(archives == null){
			archives = new ArrayList<Archive>();
		}
		archives.add(archiveDtoI);
	}
}
