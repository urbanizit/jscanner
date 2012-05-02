package org.urbanizit.jscanner.back.persistence.itf;


import java.util.Collection;
import java.util.List;

import org.urbanizit.jscanner.back.persistence.bo.PackageNameBo;


public interface PackageNameDaoItf extends GenericDaoItf<PackageNameBo, Long> {
	
	/**
	 * @param strPackageName
	 * @return
	 */
	PackageNameBo findByPackage(final String strPackageName);
		
	/**
	 * Find className in list
	 * @param classNames
	 * @return
	 */
	List<PackageNameBo> findByPackageNameIn(final Collection<String> packageNames);
}
