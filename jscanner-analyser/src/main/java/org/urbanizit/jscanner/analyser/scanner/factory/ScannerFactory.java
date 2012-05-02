package org.urbanizit.jscanner.analyser.scanner.factory;


import org.urbanizit.jscanner.ArchiveTypes;
import org.urbanizit.jscanner.analyser.scanner.AbstractArchiveScanner;
import org.urbanizit.jscanner.analyser.scanner.EARScanner;
import org.urbanizit.jscanner.analyser.scanner.JARScanner;
import org.urbanizit.jscanner.analyser.scanner.WARScanner;
import org.urbanizit.jscanner.transfert.Archive;



/**
 * @author ldassonville
 *
 */
public abstract class ScannerFactory {

	/**
	 * Retourne le scanner pour une archive donné.
	 * 
	 * @param archiveType Type de l'archive
	 * @return Scanner de l'archive
	 */
	public static AbstractArchiveScanner<? extends Archive> getArchiveScanner(Integer archiveType){
		if(archiveType == null){
			throw new IllegalArgumentException("Unkwow archiveType "+archiveType);
		}
		
		switch (archiveType) {
			case ArchiveTypes.JAR:
				return new JARScanner();
			case ArchiveTypes.WAR:
				return new WARScanner();				
			case ArchiveTypes.EAR:
				return new EARScanner();
			default:
				throw new IllegalArgumentException("Unkwow archiveType "+archiveType);
		}	
	}	
}
