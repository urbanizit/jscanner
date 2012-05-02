package org.urbanizit.jscanner.back.common.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.urbanizit.jscanner.back.common.extractor.ArchiveMethodReferenceExtractor;
import org.urbanizit.jscanner.back.common.extractor.DataExtractor;
import org.urbanizit.jscanner.back.common.resolver.pojo.MethodReferenceIdentifier;
import org.urbanizit.jscanner.back.persistence.bo.ClassNameBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodReferenceBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodSignatureBo;
import org.urbanizit.jscanner.back.persistence.itf.MethodReferenceDaoItf;
import org.urbanizit.jscanner.transfert.Archive;


public class ArchiveMethodReferenceResolver extends AbstractArchiveDataResolver<MethodReferenceIdentifier, MethodReferenceBo> {
		
	protected MethodReferenceDaoItf methodReferenceDaoItf;
	protected Map<String, ClassNameBo> classNameCache;
	protected Map<String, MethodSignatureBo> methodSignatureCache;
	
	public ArchiveMethodReferenceResolver(MethodReferenceDaoItf methodReferenceDao,  Map<String, ClassNameBo> classNameCache, Map<String, MethodSignatureBo> methodSignatureCache){
		this.methodReferenceDaoItf = methodReferenceDao;
		this.classNameCache = classNameCache;
		this.methodSignatureCache = methodSignatureCache;
	}
	
	public ArchiveMethodReferenceResolver(MethodReferenceDaoItf methodReferenceDao, boolean deepResolution, Map<String, ClassNameBo> classNameCache, Map<String, MethodSignatureBo> methodSignatureCache){
		this.methodReferenceDaoItf = methodReferenceDao;
		this.classNameCache = classNameCache;
		this.methodSignatureCache = methodSignatureCache;
		this.deepResolution = deepResolution;
	}
	
	protected static final int GROUP_SIZE = 250;
	
	protected DataExtractor<Archive, MethodReferenceIdentifier> getDataExtractor(){
		return new ArchiveMethodReferenceExtractor(deepResolution);
	}
	
	protected ResolverDataSource<MethodReferenceIdentifier, MethodReferenceBo> getResolverDataSource(){
		return new MethodReferenceDataSource();
	}
		
	protected class MethodReferenceDataSource implements ResolverDataSource<MethodReferenceIdentifier, MethodReferenceBo>{
		
		@Override
		public Collection<MethodReferenceBo> findExisting(Collection<MethodReferenceIdentifier> data) {


			Collection<String> hashCodes = getHashCodeCollection(data);			
			Collection<MethodReferenceBo> res = methodReferenceDaoItf.findByHashCodeIn(hashCodes);
			
			//FIXME avoid Hash Code collisions	
			return res;
		}
		
		@Override
		public MethodReferenceBo save(MethodReferenceIdentifier data) {

			if(classNameCache.get(data.getClassName())== null){
				System.out.println("oups...je trouve pas "+data.getClassName()+"-"+ data.getMethodSignature());
			}
			
			ClassNameBo className = classNameCache.get(data.getClassName());
			MethodSignatureBo methodSignature = methodSignatureCache.get(data.getMethodSignature());
						
			return methodReferenceDaoItf.save(
					new MethodReferenceBo(
							className, 
							methodSignature,
							data.toStringHashCode()));
		}
		

		private Collection<String> getHashCodeCollection(Collection<MethodReferenceIdentifier> data){
			Collection<String> hashCodes = new ArrayList<String>();
			
			for (MethodReferenceIdentifier mri : data) {				
				hashCodes.add(mri.toStringHashCode());
			}
			return hashCodes;
		}
	}

	@Override
	protected MethodReferenceIdentifier getDataIdentifier(MethodReferenceBo data) {
		MethodReferenceIdentifier res = new MethodReferenceIdentifier();		
		res.setClassName(data.getClassName().getClassName());
		res.setMethodSignature(data.getSignature().getMethodSignature());
		return res;
	}

}
