package org.urbanizit.jscanner.front.controller;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.urbanizit.jscanner.front.utils.comparator.ArchiveNameComparator;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ArchiveCriteria;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.itf.AnalyseServiceItf;
import org.urbanizit.jscanner.transfert.itf.CatalogServiceItf;


/**
 * @author ldassonville
 * @since 04.03.11
 */
@Controller
public class MethodController {

    private static final Logger log = LoggerFactory.getLogger(MethodController.class);

    @Inject private AnalyseServiceItf analyseServiceItfI;

    @Inject private CatalogServiceItf catalogServiceItfI;
    

    @RequestMapping(value = "/content/method/{methodId}", method = RequestMethod.GET, headers = "Accept=text/html")
    public String archiveContent(Model model, @PathVariable Long methodId) {

    	Method methodDtoI = null;
    	List<Archive> dependOnArchives = null;
    	
		try {
			methodDtoI = catalogServiceItfI.getMethod(methodId);
			
			ArchiveCriteria archiveCriteria = new ArchiveCriteria();
			archiveCriteria.setMethodCalled(Arrays.asList(methodId));
			
			dependOnArchives = analyseServiceItfI.findArchiveByCriteria(archiveCriteria, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
        model.addAttribute("method", methodDtoI); 
        
        
        sort(dependOnArchives, new ArchiveNameComparator());
        model.addAttribute("dependOnArchives", dependOnArchives);   
        return "method/content/method";
    }

    //TODO sortir d'ici (utils)
    private <T> void sort(List<T> collection, Comparator<T> comparator){    
	    if(collection != null){
	        Collections.sort(collection, comparator);
	    }
    }
}
