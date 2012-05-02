package org.urbanizit.jscanner.back.persistence.itf;

import java.util.Collection;
import java.util.List;

import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.criteria.ArchiveBoCriteria;
import org.urbanizit.jscanner.back.transfert.dto.ArchiveDependency;


public interface ArchiveDaoItf extends GenericDaoItf<ArchiveBo, Long> {

	/**
	 * Find Archive by criteria.
	 * 
	 * @param archiveCriteria Query criteria
	 * @return Archive found
	 */
	List<ArchiveBo> findByCriteria(final ArchiveBoCriteria archiveCriteria);


	List<ArchiveDependency> findDependOnArchives(final Collection<Long> archiveIds, final Collection<Long> archiveWhiteListIds);


	List<ArchiveDependency> findDependArchives(final Collection<Long> archiveIds, final Collection<Long> archiveWhiteListIds);
		
	
	/**	
	 * Give required class.
	 * @param archiveId Archive id
	 * @return Required class canonical name
	 */
	List<String> findRequiredClass(final Long archiveId);

	/**
	 * Give provided class.
	 * @param archiveId Archive Id
	 * @returnProvided class canonical name
	 */
	List<String> findProvidedClass(final Long archiveId);
		
	/**
	 * Give conflictual archives.
	 * @param archiveIds
	 * @param archiveWhiteListIds
	 * @return
	 */
	List<ArchiveDependency> findConflictualArchives(final Collection<Long> archiveIds,  final Collection<Long> archiveWhiteListIds);
}
