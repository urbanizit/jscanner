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
	
	private Collection<Long> methodProvided;
	private Collection<Long> methodCalled;
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

	public Collection<Long> getMethodProvided() {
		return methodProvided;
	}

	public void setMethodProvided(Collection<Long> methodProvided) {
		this.methodProvided = methodProvided;
	}

	public Collection<Long> getMethodCalled() {
		return methodCalled;
	}

	public void setMethodCalled(Collection<Long> methodCalled) {
		this.methodCalled = methodCalled;
	}

	public Boolean getCompagnyFile() {
		return compagnyFile;
	}

	public void setCompagnyFile(Boolean compagnyFile) {
		this.compagnyFile = compagnyFile;
	}	
}
