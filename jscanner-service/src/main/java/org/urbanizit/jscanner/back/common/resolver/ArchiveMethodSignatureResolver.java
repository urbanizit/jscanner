package org.urbanizit.jscanner.back.common.resolver;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.urbanizit.jscanner.analyser.resolver.MethodSignatureResolver;
import org.urbanizit.jscanner.back.common.extractor.ArchiveMethodSignatureExtractor;
import org.urbanizit.jscanner.back.common.extractor.DataExtractor;
import org.urbanizit.jscanner.back.common.resolver.pojo.MethodSignatureIdentifier;
import org.urbanizit.jscanner.back.persistence.bo.ClassNameBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodSignatureBo;
import org.urbanizit.jscanner.back.persistence.itf.ClassNameDaoItf;
import org.urbanizit.jscanner.back.persistence.itf.MethodSignatureDaoItf;
import org.urbanizit.jscanner.transfert.Archive;


public class ArchiveMethodSignatureResolver extends AbstractArchiveDataResolver<MethodSignatureIdentifier, MethodSignatureBo> {
		
	protected  Map<String, ClassNameBo> classNameCache; //TODO use real cache
	protected MethodSignatureDaoItf methodSignatureDaoItf;
	protected ClassNameDaoItf classNameDaoItf;
	protected static final int GROUP_SIZE = 250;
	
	public ArchiveMethodSignatureResolver(MethodSignatureDaoItf methodSignatureDaoItf){
		this.methodSignatureDaoItf = methodSignatureDaoItf;
	}
	
	public ArchiveMethodSignatureResolver(MethodSignatureDaoItf methodSignatureDaoItf, ClassNameDaoItf classNameDaoItf, Map<String, ClassNameBo> classNameCache, boolean deepResolution){
		this.methodSignatureDaoItf = methodSignatureDaoItf;
		this.classNameCache = classNameCache;
		this.deepResolution = deepResolution;
		this.classNameDaoItf = classNameDaoItf;
	}
	
	protected DataExtractor<Archive, MethodSignatureIdentifier> getDataExtractor(){
		return new ArchiveMethodSignatureExtractor(deepResolution);
	}
	
	protected ResolverDataSource<MethodSignatureIdentifier, MethodSignatureBo> getResolverDataSource(){
		return new MethodSignatureDataSource();
	}
		
	protected MethodSignatureIdentifier getDataIdentifier(MethodSignatureBo data){
		if(data != null){ 		
			return new MethodSignatureIdentifier(data.getClassName().getClassName(), data.getMethodSignature());	
		}
		return null;
	}
	
	protected class MethodSignatureDataSource implements ResolverDataSource<MethodSignatureIdentifier, MethodSignatureBo>{
		
		@Override
		public Collection<MethodSignatureBo> findExisting(Collection<MethodSignatureIdentifier> data) {
			
			Set<String> dataUIDs = new HashSet<String>();
			if(data != null){
				for (MethodSignatureIdentifier methodSignatureIdentifier : data) {
					try{
					dataUIDs.add(methodSignatureIdentifier.getClassName()+"."+MethodSignatureResolver.resolveNameSignature(methodSignatureIdentifier.getMethodSignature()));
					}catch (RuntimeException e) {
						throw e;
					}
				}				
			}
			//TODO replace by hashcode
			return methodSignatureDaoItf.findByFullyQualifiedMethodSignatureIn(dataUIDs);
		}

		@Override
		public MethodSignatureBo save(MethodSignatureIdentifier data) {
			
			String methodSignature = data.getMethodSignature();
			String readableSignature =  MethodSignatureResolver.resolveNameSignature(data.getMethodSignature());
			
			//..Hum rustine !
			ClassNameBo classNameBo = classNameCache.get(data.getClassName());
			if(classNameBo == null){
				classNameBo = classNameDaoItf.findByClassName(data.getClassName());
				if(classNameBo == null){
					classNameBo = new ClassNameBo(data.getClassName());
					classNameBo = classNameDaoItf.save(classNameBo);
					classNameCache.put(data.getClassName(), classNameBo);
				}
			}
			
			
			return methodSignatureDaoItf.save(new MethodSignatureBo(methodSignature, readableSignature, classNameBo));
		}		
	}
}
