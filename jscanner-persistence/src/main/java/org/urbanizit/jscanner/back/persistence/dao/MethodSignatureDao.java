package org.urbanizit.jscanner.back.persistence.dao;

import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;

import org.urbanizit.jscanner.back.persistence.bo.MethodSignatureBo;
import org.urbanizit.jscanner.back.persistence.itf.MethodSignatureDaoItf;


@Named
public class MethodSignatureDao extends AbstractDao<MethodSignatureBo, Long>  implements MethodSignatureDaoItf{

	@Override
	public MethodSignatureBo findByMethodSignature(final String methodSignature) {
		try{
			return findUniqueByNamedQuery("FIND_METHOD_SIGNATURE", "methodSignature", methodSignature);			
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<MethodSignatureBo> findByFullyQualifiedMethodSignatureIn(Collection<String> methodSignatures) {
		return findByNamedQuery("FIND_METHOD_SIGNATURE_IN", "methodSignatures", methodSignatures);
	}
	
}
