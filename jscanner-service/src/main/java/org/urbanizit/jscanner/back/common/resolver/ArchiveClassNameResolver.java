package org.urbanizit.jscanner.back.common.resolver;

import java.util.Collection;

import org.urbanizit.jscanner.back.common.extractor.ArchiveClassNameExtractor;
import org.urbanizit.jscanner.back.common.extractor.DataExtractor;
import org.urbanizit.jscanner.back.persistence.bo.ClassNameBo;
import org.urbanizit.jscanner.back.persistence.itf.ClassNameDaoItf;
import org.urbanizit.jscanner.transfert.Archive;



public class ArchiveClassNameResolver extends AbstractArchiveDataResolver<String, ClassNameBo> {
		
	protected ClassNameDaoItf classNameDaoItf;
	
	public ArchiveClassNameResolver(ClassNameDaoItf classNameDaoItf, boolean deepResolution){
		this.classNameDaoItf = classNameDaoItf;
		this.deepResolution = deepResolution;
	}
	
	public ArchiveClassNameResolver(ClassNameDaoItf classNameDaoItf){
		this.classNameDaoItf = classNameDaoItf;
	}
	
	protected static final int GROUP_SIZE = 250;
	
	protected DataExtractor<Archive, String> getDataExtractor(){
		return new ArchiveClassNameExtractor(deepResolution);
	}
	
	protected ResolverDataSource<String, ClassNameBo> getResolverDataSource(){
		return new ClassNameDataSource();
	}
		
	protected String getDataIdentifier(ClassNameBo data){
		if(data != null){ 		
			return data.getClassName();	
		}
		return null;
	}
	
	protected class ClassNameDataSource implements ResolverDataSource<String, ClassNameBo>{
		
		@Override
		public Collection<ClassNameBo> findExisting(Collection<String> data) {
			return classNameDaoItf.findByClassNameIn(data);
		}

		@Override
		public ClassNameBo save(String data) {
			return classNameDaoItf.save(new ClassNameBo(data));
		}		
	}
}
