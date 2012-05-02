package org.urbanizit.jscanner.back.persistence.dao;

import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;

import org.urbanizit.jscanner.back.persistence.bo.ClassNameBo;
import org.urbanizit.jscanner.back.persistence.itf.ClassNameDaoItf;


@Named
public class ClassNameDao extends AbstractDao<ClassNameBo, Long>  implements ClassNameDaoItf{

	@Override
	public ClassNameBo findByClassName(final String className) {
		try{
			return findUniqueByNamedQuery("FIND_CLASS_NAME", "className", className);			
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<ClassNameBo> findByClassNameIn(Collection<String> classNames) {
		return findByNamedQuery("FIND_CLASS_NAME_IN", "classNames", classNames);
	}
	
}
