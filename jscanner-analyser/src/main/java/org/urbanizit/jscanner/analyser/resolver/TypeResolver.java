package org.urbanizit.jscanner.analyser.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TypeResolver {

	private static Logger logger = LoggerFactory.getLogger(TypeResolver.class);
	
/*
	TypeSignature: Z | C | B | S | I | F | J | D | FieldTypeSignature
	FieldTypeSignature: ClassTypeSignature | [ TypeSignature | TypeVar
	ClassTypeSignature: L Id ( / Id )* TypeArgs? ( . Id TypeArgs? )* ;
	TypeArgs: < TypeArg+ >
	TypeArg: * | ( + | - )? FieldTypeSignature
	TypeVar: T Id 
*/
	
	private static final Character CLASS_TYPE_PREFIX = 'L';
	private static final Character VOID_TYPE_PREFIX = 'V';
	private static final Character BOOLEAN_TYPE_PREFIX = 'Z';
	private static final Character DOUBLE_TYPE_PREFIX = 'D';
	private static final Character LONG_TYPE_PREFIX = 'J';
	private static final Character FLOAT_TYPE_PREFIX = 'F';
	private static final Character INT_TYPE_PREFIX = 'I';
	private static final Character SHORT_TYPE_PREFIX = 'S';
	private static final Character BYTE_TYPE_PREFIX = 'B';
	private static final Character CHAR_TYPE_PREFIX = 'C';
	private static final Character TABLE_TYPE_PREFIX = '[';	
	private static final Character CLASS_TYPE_SEPARATOR = ';';

	private static HashMap<Character, String> primitiveTypesMap = new HashMap<Character, String>();

	static{
		primitiveTypesMap = new HashMap<Character, String>();
		primitiveTypesMap.put(VOID_TYPE_PREFIX, "void");
		primitiveTypesMap.put(BOOLEAN_TYPE_PREFIX, "boolean");
		primitiveTypesMap.put(CHAR_TYPE_PREFIX, "char");
		primitiveTypesMap.put(BYTE_TYPE_PREFIX, "byte");
		primitiveTypesMap.put(SHORT_TYPE_PREFIX, "short");
		primitiveTypesMap.put(INT_TYPE_PREFIX, "int");	
		primitiveTypesMap.put(FLOAT_TYPE_PREFIX, "float");
		primitiveTypesMap.put(LONG_TYPE_PREFIX, "long");
		primitiveTypesMap.put(DOUBLE_TYPE_PREFIX, "double");
	}
	
	private static String PRIMITIVE_TYPE_PATTERN = "("+StringUtils.join(primitiveTypesMap.keySet(),"|")+")";
	private static String OBJECT_TYPE_PATTERN = "("+CLASS_TYPE_PREFIX+"[A-Za-z0-9$_/]*"+CLASS_TYPE_SEPARATOR+")";
	private static String ARRAYS_PATTERN = "\\"+TABLE_TYPE_PREFIX+"{0,}";
	private static String TYPE_ONLY_PATTERN = "("+PRIMITIVE_TYPE_PATTERN+"|"+OBJECT_TYPE_PATTERN+")";
	private static String TYPE_PATTERN = "("+ARRAYS_PATTERN+"("+PRIMITIVE_TYPE_PATTERN+"|"+OBJECT_TYPE_PATTERN+"))";
	
	
	
	
	private static String resolveClassName(String classType){
		String res = classType.substring(1, classType.length()-1);
		res = res.replaceAll("/", ".");
		return res;
	}
	
	private static int getTabDimension(String type){
		int res = 0;
		Pattern pattern = Pattern.compile("\\"+TABLE_TYPE_PREFIX);	
		Matcher matcher = pattern.matcher(type);

		while(matcher.find()) {     
		    res++;
		}
		return res;
	}

	public static Collection<String> resolveTypes(String types){
		Collection<String> res = new ArrayList<String>();
		Collection<String> extactedTypes = extractTypes(types);
		if(extactedTypes != null){
			for (String extactedType : extactedTypes) {
				res.add(resolveType(extactedType));
			}
		}
		return res;
	}
	
	
	public static String resolveType(String type){
		if(type == null || "".equalsIgnoreCase(type)){
			return null;
		}	
		
		String typeOnly = extractTypeOnly(type);
		String resolvedType = "";
		
		if(typeOnly != null){
			if(typeOnly.startsWith(String.valueOf(CLASS_TYPE_PREFIX))){
				resolvedType = resolveClassName(typeOnly);
			}else{
				resolvedType = primitiveTypesMap.get(typeOnly.charAt(0));
			}
			
			int nbDimensions = getTabDimension(type);
			for (int i=0 ; i< nbDimensions ; i++) {
				resolvedType += "[]";
			}
		}else{
			logger.debug("Unable to get type");
		}
		return resolvedType;
	}
	
	
	/**
	 * Extract type but ignore tabs
	 * @param type Type string 
	 * @return
	 */
	private static String extractTypeOnly(String type){
		Pattern pattern = Pattern.compile(TYPE_ONLY_PATTERN);	
		Matcher matcher = pattern.matcher(type);

		if(matcher.find()) {     
	        return matcher.group();
		}
		return null;
	}
	
	public static Collection<String> extractTypes(String types){

		Collection<String> res = new ArrayList<String>();
		Pattern pattern = Pattern.compile(TYPE_PATTERN);	
		Matcher matcher = pattern.matcher(types);

		while (matcher.find()) {     
	         res.add(matcher.group());
		}
		return res;
	}
}