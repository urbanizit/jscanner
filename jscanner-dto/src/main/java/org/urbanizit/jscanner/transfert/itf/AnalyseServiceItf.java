package org.urbanizit.jscanner.transfert.itf;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ArchiveCriteria;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.Graph;
import org.urbanizit.jscanner.transfert.Method;





/**
 * Service for dependency analysis.
 * 
 * @author ldassonville
 */
public interface AnalyseServiceItf {
	
	/**
	 * Give dependency graph.
	 * @param archiveIds Archives used to compute dependencies
	 * @param archiveWhiteListIds Archive used to determinate dependencies(optional) 
	 * @return Computed dependency graph
	 * @throws MetierException
	 */
	Graph getDependencyGraph(final ArrayList<Long> archiveIds, final ArrayList<Long> archiveWhiteListIds) throws Exception;
	
	/**
	 * Give all archives who depend on the given archive. 
	 * If a white List is given, return only archive included in the white list
	 * 
	 * @param archiveId Archive Id
	 * @param archiveWhiteList Archive Id White List
	 * @return Archives depend on the give Archive
	 * @throws MetierException
	 */
	List<Archive> findDependOnArchives(final Long archiveId, final ArrayList<Long> archiveWhiteList) throws Exception;
	
	/**
	 * Give all archives used (depend) by the given archive. 
	 * If a white List is given, return only archive included in the white list
	 * 
	 * @param archiveId Archive Id
	 * @param archiveWhiteList Archive Id White List
	 * @return Archives depend on the give Archive
	 * @throws MetierException
	 */
	List<Archive> findDependArchives(final Long archiveId, final ArrayList<Long> archiveWhiteList) throws Exception;
	
	/**
	 * Find class canonicals names provided by an archive.
	 * @param archiveId Archive Id
	 * @param cascade Search in sub-archives (default : false)
	 * @return Canonicals names
	 * 
	 * @throws MetierException
	 */
	Set<String> findProvidedClassNames(final Long archiveId, final Boolean cascade) throws Exception;
	
	/**
	 * Find class canonicals names provided by an archive.
	 * 
	 * @param archiveId Archive Id
	 * @param cascade Search in sub-archives (default : false)
	 * @return Canonicals names
	 * @throws MetierException
	 */
	Set<String> findRequiredClassNames(final Long archiveId, final Boolean cascade) throws Exception;
	
	
	/**
	 * Give conflictual archive Graph
	 * @param archiveIds Archives used to find conflicts
	 * @param archiveWhiteListIds Archive used to determinate conflicts (optional) 
	 * @return Archive in conflicts
	 * @throws MetierException
	 */
	Graph getConfictGraph(final ArrayList<Long> archiveIds, final ArrayList<Long> archiveWhiteListIds) throws Exception;
		

	/**
	 * Give class in conflict between two archives
	 * @param archive1Id
	 * @param archive2Id
	 * @return
	 * @throws MetierException
	 */
	List<String> getConfictClasses(final Long archive1Id, final Long archive2Id) throws Exception;
	
	/**
	 * Give Class used by Archive1 provided by Archive2
	 * @param archive1Id
	 * @param archive2Id
	 * @return
	 * @throws MetierException
	 */
	List<String> getDependencyClasses(final Long archive1Id, final Long archive2Id) throws Exception;
	
	/**
	 * Give Methode used by Archive1 provided by Archive2
	 * @param archive1Id
	 * @param archive2Id
	 * @return
	 * @throws MetierException
	 */
	List<Method> getDependencyMethods(final Long archive1Id, final Long archive2Id) throws Exception;
	
	
	
	List<ClassFile> getClasseUser(final Long classFileId) throws Exception;
	
	
	/**
	 * Find Archive by criteria
	 * @param archiveCriteria
	 * @param deepSearch
	 * @return
	 * @throws MetierException
	 */
	List<Archive> findArchiveByCriteria(ArchiveCriteria archiveCriteria, Boolean deepSearch) throws Exception;
	
	


}
