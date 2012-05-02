package org.urbanizit.jscanner.back.persistence.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.persistence.NoResultException;

import org.urbanizit.jscanner.back.persistence.bo.MethodReferenceBo;
import org.urbanizit.jscanner.back.persistence.itf.MethodReferenceDaoItf;


@Named
public class MethodReferenceDao extends AbstractDao<MethodReferenceBo, Long>  implements MethodReferenceDaoItf{

	@Override
	public MethodReferenceBo findByClassNameAndSignature(Long className,	Long methodSignatureId) {

		try{
			Map<String, Object> valuesMap = new HashMap<String, Object>();
			valuesMap.put("signatureId", methodSignatureId);
			valuesMap.put("classNameId", className);
			return findUniqueByNamedQuery("FIND_METHOD_REFERENCES_BY_SIGNATURE_AND_CLASS_NAME", valuesMap);			
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<MethodReferenceBo> findByHashCodeIn(Collection<String> hashcodes) {
		return findByNamedQuery("FIND_METHOD_REFERENCES_BY_HASHCODE_IN", "hashcodes", hashcodes);		
	}

}
