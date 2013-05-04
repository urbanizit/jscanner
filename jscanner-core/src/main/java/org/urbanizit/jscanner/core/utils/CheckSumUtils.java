package org.urbanizit.jscanner.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;

/**
 * Checksum utils.
 * 
 * Compute checksum for given input.
 * 
 * @author Loic Dassonville
 *
 */
public class CheckSumUtils {
	
	protected final static int DEFAULT_BUFFER_SIZE = 5120;			
	protected final static String DEFAULT_FILE_ENCODING = "UTF-8";
	
	public synchronized static String getCheckSum(String valeur, CheckSumType checkSumType) throws Exception {
		return getCheckSum(valeur, checkSumType, DEFAULT_FILE_ENCODING);
	}
	
	public synchronized static String getCheckSum(String valeur, CheckSumType checkSumType, final String fileEncoding) throws Exception {
		MessageDigest md = MessageDigest.getInstance(checkSumType.getCode());
		return Hex.encodeHexString( md.digest((valeur).getBytes(fileEncoding)));
	}
	
		
	public synchronized static String getCheckSum(InputStream is, CheckSumType checkSumType) throws Exception {
		return getCheckSum(is, checkSumType, DEFAULT_FILE_ENCODING);
	}
	
	public synchronized static String getCheckSum(InputStream is, CheckSumType checkSumType, final String fileEncoding) throws Exception {
		MessageDigest md = MessageDigest.getInstance(checkSumType.getCode());
		md.reset();
		
		int nread = 0; 	
	    byte[] dataBytes = new byte[DEFAULT_BUFFER_SIZE];	 
	    
	    while ((nread = is.read(dataBytes)) != -1) {
	      md.update(dataBytes, 0, nread);
	    };	
		return Hex.encodeHexString( md.digest());
	}
	
	

	public synchronized static String getCheckSum(File file, CheckSumType checkSumType) throws Exception {
		return getCheckSum(file, checkSumType, DEFAULT_FILE_ENCODING);
	}
	
	public synchronized static String getCheckSum(File file, CheckSumType checkSumType, final String fileEncoding) throws Exception {
		InputStream inputStream = null;
		try{
			inputStream = new FileInputStream(file);
			return getCheckSum(inputStream, checkSumType, fileEncoding);
		}finally{
			IOUtils.closeQuietly(inputStream);
		}
	}
}
