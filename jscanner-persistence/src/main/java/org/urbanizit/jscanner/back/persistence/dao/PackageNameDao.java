package org.urbanizit.jscanner.back.persistence.dao;

import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;

import org.urbanizit.jscanner.back.persistence.bo.PackageNameBo;
import org.urbanizit.jscanner.back.persistence.itf.PackageNameDaoItf;


@Named
public class PackageNameDao extends AbstractDao<PackageNameBo, Long>  implements PackageNameDaoItf{

	@Override
	public PackageNameBo findByPackage(final String packageName) {
		try{
			return findUniqueByNamedQuery("FIND_PACKAGE_NAME", "packageName", packageName);
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<PackageNameBo> findByPackageNameIn(Collection<String> packageNames) {
		try{
			return findByNamedQuery("FIND_PACKAGE_NAME_IN", "packageNames", packageNames);
		}catch (NoResultException e) {
			return null;
		}
	}
}
