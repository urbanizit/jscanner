package org.urbanizit.jscanner.front.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.urbanizit.jscanner.front.utils.comparator.ArchiveNameComparator;
import org.urbanizit.jscanner.front.utils.comparator.ClassFileCNComparator;
import org.urbanizit.jscanner.front.utils.comparator.MethodeCNComparator;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ArchiveCriteria;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.NestableArchive;
import org.urbanizit.jscanner.transfert.itf.AnalyseServiceItf;
import org.urbanizit.jscanner.transfert.itf.ArchiveServiceItf;
import org.urbanizit.jscanner.transfert.itf.CatalogServiceItf;


/**
 * @author ldassonville
 * @since 04.03.11
 */
@Controller
public class ArchiveController {


    private static final Logger log = LoggerFactory.getLogger(ArchiveController.class);

    @Inject private AnalyseServiceItf analyseServiceItfI;

    @Inject private ArchiveServiceItf archiveServiceItfI;

    @Inject private CatalogServiceItf catalogServiceItfI;
    
    
    @RequestMapping(value = "/content/archive/{archiveId}", method = RequestMethod.GET, headers = "Accept=text/html")
    public String archiveContent(Model model, @PathVariable Long archiveId) {

    	Archive archiveDtoI = null;
    	Collection<Archive> nestedArchives = null;
    	Collection<Archive> dependArchives = null;
    	Collection<Archive> dependOnArchives = null;
    	
		try {
			archiveDtoI = catalogServiceItfI.getArchive(archiveId, true);
			if(archiveDtoI != null){
				Collections.sort(archiveDtoI.getClassFiles(), new ClassFileCNComparator());
				
				if(archiveDtoI != null && (archiveDtoI instanceof NestableArchive)){
					nestedArchives = ((NestableArchive) archiveDtoI).getSubArchives();
				}
				
				dependArchives = accumulate(dependArchives, analyseServiceItfI.findDependArchives(archiveId, null)) ;
				dependOnArchives = accumulate(dependOnArchives, analyseServiceItfI.findDependOnArchives(archiveId, null));
					
				if(nestedArchives != null){										
					for (Archive subArchive :  nestedArchives) {
						dependArchives = accumulate(dependArchives, analyseServiceItfI.findDependArchives(subArchive.getId(), null)) ;
						dependOnArchives = accumulate(dependOnArchives, analyseServiceItfI.findDependOnArchives(subArchive.getId(), null));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        model.addAttribute("archive", archiveDtoI);
        
        nestedArchives = sort(nestedArchives, new ArchiveNameComparator());
        model.addAttribute("nestedArchives", nestedArchives);   

        dependArchives = sort(dependArchives, new ArchiveNameComparator());
        model.addAttribute("dependArchives", dependArchives);   
               
        dependOnArchives = sort(dependOnArchives, new ArchiveNameComparator());
        model.addAttribute("dependOnArchives", dependOnArchives);   
        
        return "archive/content/archive";
    }
     
    
    @RequestMapping(value = "/explain/dependency/archive/{archiveId}/{dependArchiveId}", method = RequestMethod.GET, headers = "Accept=text/html")
    public String explainDependency(Model model, @PathVariable Long archiveId, @PathVariable Long dependArchiveId) {
    	
    	List<String> classDependencies = null;
    	List<Method> methodDependencies = null;
    	try{
    		
    		//recuperation du detail des archives a comparer
    		ArchiveCriteria criteria = new ArchiveCriteria();
    		criteria.setArchiveIds(Arrays.asList(archiveId, dependArchiveId ));
    		Map<Long, Archive> mapArchives = mapArchivesById(archiveServiceItfI.findArchiveByCriteria(criteria));
    		
    		model.addAttribute("archive", mapArchives.get(archiveId)); 
    		model.addAttribute("dependArchive", mapArchives.get(dependArchiveId)); 
    		    
	    	classDependencies = analyseServiceItfI.getDependencyClasses(archiveId, dependArchiveId);
	    	
	    	methodDependencies = analyseServiceItfI.getDependencyMethods(dependArchiveId, archiveId);
	    } catch (Exception e) {
			e.printStackTrace();
		}
	    model.addAttribute("classDependencies", classDependencies);  

        sort(methodDependencies, new MethodeCNComparator());
	    model.addAttribute("methodDependencies", methodDependencies);  
	  
    	return "archive/dependency/archive";
    }
   
    
    
    protected List<Archive> lastVersionFilter(List<Archive> archive){
    	List<Archive> res = new ArrayList<Archive>();
    	

    	
    	return res;
    }
    /**
     * Accumulator elements (initialize accumulator if required).
     * @param accumulateur
     * @param addedElements
     * @return
     */
    protected <T> Collection<T> accumulate(Collection<T> accumulateur, Collection<T> addedElements){
    	if(addedElements == null){ return accumulateur;}
    	
    	if(accumulateur == null){
    		accumulateur = new HashSet<T>();
    	}
    	accumulateur.addAll(addedElements);
    	return accumulateur;
    }
    
    /**
     * 
     * @param archives
     * @return
     */
    protected Map<Long, Archive> mapArchivesById(Collection<Archive> archives){
    	Map<Long, Archive> res = new HashMap<Long, Archive>();
    	if(archives != null){
    		for (Archive archiveDtoI : archives) {
				res.put(archiveDtoI.getId(), archiveDtoI);
			}    		
    	}    	
    	return res;
    }
    
    /**
     * 
     * @param collection
     * @param comparator
     * @return
     */
    private <T> List<T> sort(Collection<T> collection, Comparator<T> comparator){    
    	List<T> res = null;
	    if(collection != null){
	    	if(!(collection instanceof List)){
	    		res = new ArrayList<T>(collection);
	    	}else{
	    		res = (List<T>) collection;
	    	}
	        Collections.sort(res, comparator);
	    }
	    return res;
    }
}
