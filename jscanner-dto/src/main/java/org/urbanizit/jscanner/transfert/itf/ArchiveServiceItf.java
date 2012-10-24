package org.urbanizit.jscanner.transfert.itf;

import java.io.Serializable;
import java.util.List;

import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ArchiveCriteria;




/**
 * Archive Service.
 * 
 * @author ldassonville
 */
public interface ArchiveServiceItf extends Serializable {
	
	/**
	 * Register an archive.
	 * 
	 * @param archive Archive to register
	 * @return archive Id 
	 */
	public Long registerArchive(Archive archive)throws Exception;

	/**
	 * Search archive by criteria.
	 * 
	 * @param criteriaDtoI
	 * @return
	 * @throws Exception
	 */
	public List<Archive> findArchiveByCriteria(ArchiveCriteria criteriaDtoI)throws Exception;

	
	public Long saveArchiveNonRecursif(Archive archiveDtoI )throws Exception;
}
