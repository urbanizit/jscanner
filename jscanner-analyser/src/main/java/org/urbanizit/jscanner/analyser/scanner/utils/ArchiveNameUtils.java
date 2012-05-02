package org.urbanizit.jscanner.analyser.scanner.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ArchiveNameUtils {
	
	private static String VERSION_PATTERN = "(-(\\d+\\.)*(\\d+))?";
	private static String SNAPSHOT_PATTERN = "(-SNAPSHOT)?";
	private static String EXTENSION_PATTERN = "\\.(jar|ear|war)";

	private static String ARCHIVE_NAME_PATTERN = VERSION_PATTERN+SNAPSHOT_PATTERN+EXTENSION_PATTERN; 
	
	public static String getExtension(final String filename){
				
		String[] elts = filename.split("\\.");
		if(elts.length > 1){
			 return elts[elts.length-1];
		}
		return null;
	}
	
	public static String getBasicComponentName(String filename){
		
		Pattern archiveNamePattern  = Pattern.compile(ARCHIVE_NAME_PATTERN, Pattern.CASE_INSENSITIVE);

		Matcher matcher = archiveNamePattern.matcher(filename);
		if(matcher.find()){		
			 return filename.substring(0, matcher.start());
		}
		return filename;
	}	
}
