package org.urbanizit.jscanner.analyser.scanner.utils;

import java.io.InputStream;
import java.security.MessageDigest;

import org.apache.commons.io.IOUtils;

public class CheckSumUtils {
	
	public synchronized static String getMd5sum(String valeur) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		return byteToHex(md.digest((valeur).getBytes("UTF-8")));
	}

	public synchronized static String getSha256(String valeur) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return byteToHex(md.digest((valeur).getBytes("UTF-8")));
	}
		
	public synchronized static String getSha256(InputStream is) throws Exception {
			
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(IOUtils.toByteArray(is));		
		return byteToHex(md.digest());
	}

	
	public static String byteToHex(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		StringBuffer hex = new StringBuffer(); // encod(1_bit) =>
		// 2 digits
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) { // 0 < .. < 9
				hex.append("0");
			}
			hex.append(Integer.toString((int) bytes[i] & 0xff, 16)); // [(bit+256)%256]^16
		}
		return hex.toString();
	}
}
