package org.urbanizit.jscanner.analyser.scanner.utils;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.urbanizit.jscanner.core.utils.CheckSumType;
import org.urbanizit.jscanner.core.utils.CheckSumUtils;


/**
 * Tests for CheckSumUtils.
 * 
 * @author Loic Dassonville
 *
 */
public class CheckSumTest{
    
	private Logger logger = LoggerFactory.getLogger(CheckSumTest.class);
	
    @Test
	public void sha256Test() {
		
		try {
			File file = new File("P:\\Miroir_GAV\\courtier-front.war");
			String checkSum = CheckSumUtils.getCheckSum(file, CheckSumType.SHA256);
			Assert.assertEquals("d531af73363329f1c140f757e95c7e6dc77c0e70903a4adc4d2dfa226bd69b77", checkSum);
			
		} catch (Exception e) {
			logger.error("Error findArchiveTest", e);
			Assert.fail("An error occur while computing SHA256 checksum");
		}

	}  
    
    @Test
	public void sha1Test() {
		
		try {
			File file = new File("P:\\Miroir_GAV\\courtier-front.war");
			String checkSum = CheckSumUtils.getCheckSum(file, CheckSumType.SHA1);
			Assert.assertEquals("b4299b535f750dfbf16e76ad46b92b8bd128a69c", checkSum);
			
		} catch (Exception e) {
			logger.error("Error findArchiveTest", e);
			Assert.fail("An error occur while computing SHA1 checksum");
		}
	}    
    
    
    @Test
	public void md5Test() {
		
		try {
			File file = new File("P:\\Miroir_GAV\\courtier-front.war");
			String checkSum = CheckSumUtils.getCheckSum(file, CheckSumType.MD5);
			Assert.assertEquals("a75105532e763392287ec7cda3c77f40", checkSum);
			
		} catch (Exception e) {
			logger.error("Error findArchiveTest", e);
			Assert.fail("An error occur while computing MD5 checksum");
		}
	}   
}
