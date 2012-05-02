package org.urbanizit.jscanner.back.common.resolver;

import java.util.Collection;

import org.urbanizit.jscanner.analyser.resolver.MethodSignatureResolver;
import org.urbanizit.jscanner.back.common.extractor.ArchiveMethodSignatureExtractor;
import org.urbanizit.jscanner.back.common.extractor.DataExtractor;
import org.urbanizit.jscanner.back.persistence.bo.MethodSignatureBo;
import org.urbanizit.jscanner.back.persistence.itf.MethodSignatureDaoItf;
import org.urbanizit.jscanner.transfert.Archive;


public class ArchiveMethodSignatureResolver extends AbstractArchiveDataResolver<String, MethodSignatureBo> {
		
	protected MethodSignatureDaoItf methodSignatureDaoItf;
	protected static final int GROUP_SIZE = 250;
	
	public ArchiveMethodSignatureResolver(MethodSignatureDaoItf methodSignatureDaoItf){
		this.methodSignatureDaoItf = methodSignatureDaoItf;
	}
	
	public ArchiveMethodSignatureResolver(MethodSignatureDaoItf methodSignatureDaoItf, boolean deepResolution){
		this.methodSignatureDaoItf = methodSignatureDaoItf;
		this.deepResolution = deepResolution;
	}
	
	protected DataExtractor<Archive, String> getDataExtractor(){
		return new ArchiveMethodSignatureExtractor(deepResolution);
	}
	
	protected ResolverDataSource<String, MethodSignatureBo> getResolverDataSource(){
		return new MethodSignatureDataSource();
	}
		
	protected String getDataIdentifier(MethodSignatureBo data){
		if(data != null){ 		
			return data.getMethodSignature();	
		}
		return null;
	}
	
	protected class MethodSignatureDataSource implements ResolverDataSource<String, MethodSignatureBo>{
		
		@Override
		public Collection<MethodSignatureBo> findExisting(Collection<String> data) {
			return methodSignatureDaoItf.findByMethodSignatureIn(data);
		}

		@Override
		public MethodSignatureBo save(String data) {
			return methodSignatureDaoItf.save(new MethodSignatureBo(data, MethodSignatureResolver.resolveNameSignature(data)));
		}		
	}
}
