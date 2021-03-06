package org.urbanizit.jscanner.front.controller;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ArchiveCriteria;
import org.urbanizit.jscanner.transfert.itf.AnalyseServiceItf;


/**
 * @author ldassonville
 * @since 04.03.11
 */
@Controller
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    @Inject
    private AnalyseServiceItf analyseServiceItfI;


    @RequestMapping(value = "/search/classname/", method = RequestMethod.GET, headers = "Accept=text/html")
    public String searchByClassNameForm(Model model) {
    	 return "search/classname/form";
    }
    
    @RequestMapping(value = "/search/method/", method = {RequestMethod.GET, RequestMethod.POST}, headers = "Accept=text/html")
    public String searchByMethodName(Model model,  @RequestParam(required=false, value = "methodName") String classname) {
    	return "search/method/form";
    }
    
    @RequestMapping(value = "/search/classname/", method = {RequestMethod.GET, RequestMethod.POST}, headers = "Accept=text/html")
    public String searchByClassName(Model model,  @RequestParam(required=false, value = "className") String classname) {

    	if(classname != null){
    		model.addAttribute("searchComplete", true);
	    	ArchiveCriteria archiveCriteria = new ArchiveCriteria();
	    	archiveCriteria.setCanonicalNames(Arrays.asList(classname));
	    	List<Archive> archives = null;
			try {
				archives = analyseServiceItfI.findArchiveByCriteria(archiveCriteria, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("archives", archives);
    	}
        return "search/classname/form";
    }
    
    
    
    @RequestMapping(value = "/search/archivename/",  method = {RequestMethod.GET, RequestMethod.POST}, headers = "Accept=text/html")
    public String searchByArchiveName(Model model,  @RequestParam(required=false, value = "archiveName") String archiveName) {

    	if(archiveName != null){
    		model.addAttribute("searchComplete", true);
    		ArchiveCriteria archiveCriteria = new ArchiveCriteria();
        	archiveCriteria.setArchiveNames(Arrays.asList(archiveName));
        	List<Archive> archives = null;
    		try {
    			archives = analyseServiceItfI.findArchiveByCriteria(archiveCriteria, false);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		model.addAttribute("archives", archives);
    	}
       
        return "search/archive/form";
    }
}
