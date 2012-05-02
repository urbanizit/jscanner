package org.urbanizit.jscanner.back.common.resolver;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.back.common.extractor.DataExtractor;
import org.urbanizit.jscanner.back.common.utils.CollectionsUtils;
import org.urbanizit.jscanner.transfert.Archive;


public abstract class AbstractArchiveDataResolver<S, D> implements BussinessResolver<Archive, S, D> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected static final int GROUP_SIZE = 250;
	
	protected boolean deepResolution;
	
	protected abstract DataExtractor<Archive, S> getDataExtractor();
	
	protected abstract ResolverDataSource<S, D> getResolverDataSource();
		
	protected abstract S getDataIdentifier(D data);
		
	protected AbstractArchiveDataResolver(){
		this.deepResolution = true;
	}
	

	public boolean isDeepResolution() {
		return deepResolution;
	}

	public void setDeepResolution(boolean deepResolution) {
		this.deepResolution = deepResolution;
	}

	public Map<S, D> resolve(final Archive archive){
		return resolve(archive, getDataExtractor(), getResolverDataSource());
	}
			
	@SuppressWarnings("unchecked")
	private Map<S, D> resolve(final Archive archiveDtoI, final  DataExtractor<Archive, S> extractor, ResolverDataSource<S, D> dataSource){
		
		Long begintime = System.currentTimeMillis();
		
		logger.debug("Begin : ArchiveDataResolver");

		Collection<S> usedClassNames = new HashSet<S>();
		extractor.extractData(archiveDtoI, usedClassNames);

		List<List<S>> subLists = CollectionsUtils.splitList(usedClassNames, GROUP_SIZE);
		Map<S, D> res = new HashMap<S, D>();
		Set<S> existingElts = new HashSet<S>();
		Set<S> nonExistingElts = new HashSet<S>();
		
		//Searching existing database element
		for (List<S> subList : subLists) {							
			Collection<D> findedClassNames = dataSource.findExisting(subList);		
						
			for (D findedClassName  : findedClassNames) {
				S dataIdentifier = getDataIdentifier(findedClassName);
				res.put(dataIdentifier, findedClassName);
				existingElts.add(dataIdentifier);
			}				
			nonExistingElts.addAll(CollectionUtils.subtract(subList, existingElts));	
			// CollectionUtils.disjunction(subList, existingElts);			
			existingElts.clear();			
		}
	
		//Searching non existing database elements
		for (S nonExistingElt : nonExistingElts) {
			if(nonExistingElt != null){
				res.put(nonExistingElt, dataSource.save(nonExistingElt));
			}else{
				res.put(nonExistingElt, null);
			}
		}
		
		logger.debug("End : ArchiveDataResolver ( % s )", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-begintime));	

		return res;		
	}
}
