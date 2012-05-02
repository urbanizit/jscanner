package org.urbanizit.jscanner.transfert;

import java.util.List;


public class ArchiveDependencyReport {
		
	private Archive archiveProvider;
	private Archive archiveCustormer;
	
	private List<ClassDependencyReport> classDependencyReportDtoIs;

	public Archive getArchiveProvider() {
		return archiveProvider;
	}

	public void setArchiveProvider(Archive archiveProvider) {
		this.archiveProvider = archiveProvider;
	}

	public Archive getArchiveCustormer() {
		return archiveCustormer;
	}

	public void setArchiveCustormer(Archive archiveCustormer) {
		this.archiveCustormer = archiveCustormer;
	}

	public List<ClassDependencyReport> getClassDependencyReportDtoIs() {
		return classDependencyReportDtoIs;
	}

	public void setClassDependencyReportDtoIs(
			List<ClassDependencyReport> classDependencyReportDtoIs) {
		this.classDependencyReportDtoIs = classDependencyReportDtoIs;
	}

}
