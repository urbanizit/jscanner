package org.urbanizit.jscanner.analyser.scanner.utils;

public abstract class ClassUtils {

	private static final String PACKAGE_SEPARATOR = ".";
	
	/**
	 * Extract class package.
	 * @param className ClassName
	 * @return package Class package
	 */
	public static String extractPackage(final String className) {		
		int pkgIdx = className.lastIndexOf(PACKAGE_SEPARATOR);
		if (pkgIdx != -1) {
			return className.substring(0, pkgIdx);
		}
		return null;
	}
	
	/**
	 * Extract class simple name.
	 * @param className ClassName
	 * @return simple className 
	 */
	public static String extractSimpleName(final String className) {
		int pkgIdx = className.lastIndexOf(PACKAGE_SEPARATOR);
		if (pkgIdx != -1) {
			return className.substring(pkgIdx+1);
		}
		return null;
	}
}
