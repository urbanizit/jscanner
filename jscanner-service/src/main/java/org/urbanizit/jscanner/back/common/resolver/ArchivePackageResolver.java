package org.urbanizit.jscanner.back.common.resolver;

import java.util.Collection;

import org.urbanizit.jscanner.back.common.extractor.ArchivePackageExtractor;
import org.urbanizit.jscanner.back.common.extractor.DataExtractor;
import org.urbanizit.jscanner.back.persistence.bo.PackageNameBo;
import org.urbanizit.jscanner.back.persistence.itf.PackageNameDaoItf;
import org.urbanizit.jscanner.transfert.Archive;


public class ArchivePackageResolver extends AbstractArchiveDataResolver<String, PackageNameBo> {
		
	protected PackageNameDaoItf packageNameDaoItf;
	
	public ArchivePackageResolver(PackageNameDaoItf packageNameDaoItf){
		this.packageNameDaoItf = packageNameDaoItf;
	}
	
	public ArchivePackageResolver(PackageNameDaoItf packageNameDaoItf, boolean deepResolution){
		this.packageNameDaoItf = packageNameDaoItf;
		this.deepResolution = deepResolution;
	}

	protected DataExtractor<Archive, String> getDataExtractor(){
		return new ArchivePackageExtractor(deepResolution);
	}
	
	protected ResolverDataSource<String, PackageNameBo> getResolverDataSource(){
		return new PackageDataSource();
	}
		
	protected String getDataIdentifier(PackageNameBo data){
		if(data != null){ 		
			return data.getPackageName();
		}
		return null;
	}
	
	protected class PackageDataSource implements ResolverDataSource<String, PackageNameBo>{
		
		@Override
		public Collection<PackageNameBo> findExisting(Collection<String> data) {
			return packageNameDaoItf.findByPackageNameIn(data);
		}

		@Override
		public PackageNameBo save(String data) {
			return packageNameDaoItf.save(new PackageNameBo(data));
		}		
	}
}
