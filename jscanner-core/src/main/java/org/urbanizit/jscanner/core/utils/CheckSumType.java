package org.urbanizit.jscanner.core.utils;

/**
 * Define the check sum type.
 * @author Loic DASSONVILLE
 *
 */
public enum CheckSumType {
		
	MD5("MD5"),
	SHA256("SHA-256"),
	SHA1("SHA-1");
	
	private String code;
	
	private CheckSumType(String code){
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
