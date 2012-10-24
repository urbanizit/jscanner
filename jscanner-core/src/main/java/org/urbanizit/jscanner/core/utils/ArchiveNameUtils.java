package org.urbanizit.jscanner.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ArchiveNameUtils {
		
	private static final String VERSION_QUALIFIER_STR_PATTERN = "(-(SNAPSHOT|RELEASE|BETA|ALPHA|FINAL))?";
	private static final String VERSION_STR_PATTERN = "(-(\\d)*(\\.(\\d)*)*)?";
	private static final String EXTENSION_STR_PATTERN = ".(jar|war|ear)$";
	private static final String ARCHIVE_NAME_STR_PATTERN = "^([^\\s]+)";
	private static final String ARCHIVE_SUFFIXES_STR_PATTERN = VERSION_STR_PATTERN+VERSION_QUALIFIER_STR_PATTERN+EXTENSION_STR_PATTERN; 
	private static final String ARCHIVE_FULL_NAME_STR_PATTERN = ARCHIVE_NAME_STR_PATTERN + ARCHIVE_SUFFIXES_STR_PATTERN; 
	
	
	private static Pattern ARCHIVE_SUFFIXES_PATTERN  = Pattern.compile(ARCHIVE_SUFFIXES_STR_PATTERN, Pattern.CASE_INSENSITIVE);
	private static Pattern ARCHIVE_FULL_NAME_PATTERN  = Pattern.compile(ARCHIVE_FULL_NAME_STR_PATTERN, Pattern.CASE_INSENSITIVE);
	
	public static String getExtension(final String filename){
				
		String[] elts = filename.split("\\.");
		if(elts.length > 1){
			 return elts[elts.length-1];
		}
		return null;
	}
	
	public static boolean isValidFileName(final String filename){
		if(filename == null){ return false;}
		
		return ARCHIVE_FULL_NAME_PATTERN.matcher(filename).matches();
		
	}
	
	public static String getComponentBaseName(final String filename){
		if(!isValidFileName(filename)){
			throw new IllegalArgumentException("Invalid filename");
		}
		
		Matcher matcher = ARCHIVE_SUFFIXES_PATTERN.matcher(filename);
		if(matcher.find()){		
			 return filename.substring(0, matcher.start());
		}
		return filename;
	}	
}
