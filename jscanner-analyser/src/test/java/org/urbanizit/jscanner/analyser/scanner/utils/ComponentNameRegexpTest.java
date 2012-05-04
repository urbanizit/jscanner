package org.urbanizit.jscanner.analyser.scanner.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class ComponentNameRegexpTest {


	String sVersionQualifierPattern = "(-(SNAPSHOT|RELEASE|BETA|ALPHA|FINAL))?";
	String sVersionPattern = "(-(\\d)*(\\.(\\d)*)*)?";
	String sArchiveExtensionPattern = ".(jar|war|ear)$";
	String sComponentPattern = "^([^\\s]+)";
	
	private Pattern componentNamePattern = Pattern.compile(sComponentPattern, Pattern.CASE_INSENSITIVE);
	private Pattern versionPattern = Pattern.compile(sVersionPattern, Pattern.CASE_INSENSITIVE);
	private Pattern versionQualifierPattern = Pattern.compile(sVersionQualifierPattern, Pattern.CASE_INSENSITIVE);
		

	@Test
	public void componentName(){
		
		String [] componentNames = {"extranet", "extranet-common", "extranet_common", "EXTRANET_DEMO" };
		
		for (String componentName : componentNames) {
			Matcher m = componentNamePattern.matcher(componentName);
			Assert.assertTrue("Invalid component name validation : "+componentName, m.matches());
		}		
	}
	
	@Test
	public void checkSuffix(){
		
		String [] versionQualifiers = {"-Snapshot", "-SNAPSHOT", "-RELEASE", "-beta", "-BETA", "-final", "-Alpha" };
		
		for (String versionQualifier : versionQualifiers) {
			Matcher m = versionQualifierPattern.matcher(versionQualifier);
			Assert.assertTrue("Invalid version qualifier validation : "+versionQualifier, m.matches());
		}		
	}
	
	
	@Test
	public void checkVersions(){
		
		String [] versions = {"", "-1.55", "-1.2.3", "-1.52.23", "-1" };
		
		for (String version : versions) {
			Matcher m = versionPattern.matcher(version);
			Assert.assertTrue("Invalid version validation : "+versionPattern, m.matches());
		}		
	}
	
	
	@Test
	public void getComponentBaseName_validCase(){
		
		Map<String, String[]> testCases = new HashMap<String, String[]>();
		testCases.put("toto", new String[]{"toto-1.2.3.jar", "toto-1.2.3-SNAPSHOT.jar" });
		testCases.put("toto-sample", new String[]{"toto-sample.ear", "toto-sample-1.2.3.jar", "toto-sample-1.2.3-SNAPSHOT.jar" });
				
		for (Map.Entry<String, String[]> entry: testCases.entrySet()) {
			
			for (String value : entry.getValue()) {
				Assert.assertEquals("Invalid getComponentBaseName validation ", entry.getKey(), ArchiveNameUtils.getComponentBaseName(value));
			}			
			
		}		
	}
	
	@Test
	public void isValidFileName_valideTestCases(){
		
		String [] fullNames = {"toto-1.2.3.jar", "toto-1.2.3-SNAPSHOT.jar", "my-sample-Release.jar", "myEar.ear" };
				
		for (String fullName : fullNames) {
			Assert.assertTrue("Invalid fullname validation : "+fullName, ArchiveNameUtils.isValidFileName(fullName));
		}		
	}
	
	@Test	
	public void isValidFileName_invalideTestCases(){
		
		String [] fullNames = {"", "toto-1.2.3-SNAPSHOT.zar" };
				
		for (String fullName : fullNames) {			
			Assert.assertFalse("Invalid fullname error validation : "+fullName, ArchiveNameUtils.isValidFileName(fullName));
		}		
	}
}


