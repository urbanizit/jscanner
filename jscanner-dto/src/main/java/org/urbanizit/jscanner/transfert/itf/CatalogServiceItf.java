package org.urbanizit.jscanner.transfert.itf;

import java.util.List;

import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.view.ArchiveView;




/**
 * Catalog Service.
 * 
 * @author Loic Dassonville
 */
public interface CatalogServiceItf {
	
	
	Archive getArchive(final Long archiveId, final List<ArchiveView> views)	throws Exception;
	
	ClassFile getClassFile(final Long classFileId) throws Exception;
	
	Method getMethod(final Long methodId) throws Exception;
	

}
