package org.urbanizit.jscanner.back.common.extractor;

import java.util.Collection;

import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.NestableArchive;


public abstract class AbstractArchiveDataExtractor<E> implements DataExtractor<Archive, E> {

	protected boolean isRecursiveExtraction = true;
	
	protected abstract DataExtractor<ClassFile, E> getExtractor();
	
	public boolean isRecursiveExtraction() {
		return isRecursiveExtraction;
	}

	public void setRecursiveExtraction(boolean isRecursiveExtraction) {
		this.isRecursiveExtraction = isRecursiveExtraction;
	}

	@Override
	public void extractData(Archive archive, Collection<E> accumulator) {
		 extractData(archive, accumulator, getExtractor());
	}
	
	private void extractData(Archive archive, Collection<E> accumulator, DataExtractor<ClassFile, E> extractor) {
		
		if(archive.getClassFiles()!= null){
			for (ClassFile classFile : archive.getClassFiles()) {
				extractor.extractData(classFile, accumulator);		
			}
		}
		
		if(isRecursiveExtraction()){
			if(archive instanceof NestableArchive){			
				NestableArchive nestableArchiveDtoI = (NestableArchive)archive;
				if(nestableArchiveDtoI.getSubArchives()!= null){
					for (Archive subArchive :  nestableArchiveDtoI.getSubArchives()) {
						extractData(subArchive, accumulator);
					}				
				}
			}	
		}
	}
}
