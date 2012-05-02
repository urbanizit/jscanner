package org.urbanizit.jscanner.analyser.resolver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public abstract class MethodSignatureResolver {

	private MethodSignatureResolver(){};
	
    private static String METHOD_SIGNATURE_PATTERN = "(.*)(\\(.*\\))(.*)";

    public static String resolveNameSignature(String signature){
    	Pattern pattern = Pattern.compile(METHOD_SIGNATURE_PATTERN);
		Matcher m = pattern.matcher(signature);
		String methodeName = null, parameterType = null, resultType = null;
		 
		if(m.matches()){
			 methodeName = m.group(1);
			 parameterType = m.group(2);
			 parameterType = parameterType.substring(1, parameterType.length()-1);
			 resultType = m.group(3);
		}
		String res = TypeResolver.resolveType(resultType) + " " + methodeName + "("+StringUtils.join(TypeResolver.resolveTypes(parameterType),",")+")" ;
		return res;
    }
}