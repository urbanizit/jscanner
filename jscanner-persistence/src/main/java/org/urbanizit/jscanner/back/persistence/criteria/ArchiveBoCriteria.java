package org.urbanizit.jscanner.back.persistence.criteria;

import java.io.Serializable;
import java.util.Collection;

public class ArchiveBoCriteria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String checksum;
	private Collection<String> archiveNames;
	private Collection<Long> archiveIds;
	private Collection<String> classNames;
	private Collection<String> packageNames;
	private Collection<String> canonicalNamesDependencies;
	private String providedMethodName;
	private String consumedMethodName;
	
	private Collection<Long> providedMethodIds;
	private Collection<Long> calledMethodIds;
		
	
	private Boolean compagnyFile;
	
	public void setArchiveIds(Collection<Long> archiveIds) {
		this.archiveIds = archiveIds;
	}

	public Collection<Long> getArchiveIds() {
		return archiveIds;
	}	
	
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setClassNames(Collection<String> classNames) {
		this.classNames = classNames;
	}

	public Collection<String> getClassNames() {
		return classNames;
	}

	public void setPackageNames(Collection<String> packageNames) {
		this.packageNames = packageNames;
	}

	public Collection<String> getPackageNames() {
		return packageNames;
	}

	public void setCanonicalNamesDependencies(
			Collection<String> canonicalNamesDependencies) {
		this.canonicalNamesDependencies = canonicalNamesDependencies;
	}

	public Collection<String> getCanonicalNamesDependencies() {
		return canonicalNamesDependencies;
	}

	public Collection<String> getArchiveNames() {
		return archiveNames;
	}

	public void setArchiveNames(Collection<String> archiveNames) {
		this.archiveNames = archiveNames;
	}

	public String getProvidedMethodName() {
		return providedMethodName;
	}

	public void setProvidedMethodName(String providedMethodName) {
		this.providedMethodName = providedMethodName;
	}

	public String getConsumedMethodName() {
		return consumedMethodName;
	}

	public void setConsumedMethodName(String consumedMethodName) {
		this.consumedMethodName = consumedMethodName;
	}
	
	public Collection<Long> getProvidedMethodIds() {
		return providedMethodIds;
	}

	public void setProvidedMethodIds(Collection<Long> providedMethodIds) {
		this.providedMethodIds = providedMethodIds;
	}

	public Collection<Long> getCalledMethodIds() {
		return calledMethodIds;
	}

	public void setCalledMethodIds(Collection<Long> calledMethodIds) {
		this.calledMethodIds = calledMethodIds;
	}

	public Boolean getCompagnyFile() {
		return compagnyFile;
	}

	public void setCompagnyFile(Boolean compagnyFile) {
		this.compagnyFile = compagnyFile;
	}	
}
