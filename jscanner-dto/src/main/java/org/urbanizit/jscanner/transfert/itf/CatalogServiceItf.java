package org.urbanizit.jscanner.transfert.itf;

import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.Method;




/**
 * Catalog Service.
 * 
 * @author Loic Dassonville
 */
public interface CatalogServiceItf {
	
	

	Archive getArchive(final Long archiveId, final Boolean deepSearch)	throws Exception;
	
	ClassFile getClassFile(final Long classFileId) throws Exception;
	
	Method getMethod(final Long methodId) throws Exception;
	

}
