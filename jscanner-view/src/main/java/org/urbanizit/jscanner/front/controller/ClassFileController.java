package org.urbanizit.jscanner.front.controller;


import java.util.Collections;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.urbanizit.jscanner.front.utils.comparator.MethodMNComparator;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.itf.AnalyseServiceItf;
import org.urbanizit.jscanner.transfert.itf.CatalogServiceItf;


/**
 * @author ldassonville
 * @since 04.03.11
 */
@Controller
public class ClassFileController {

    private static final Logger log = LoggerFactory.getLogger(ClassFileController.class);

    @Inject  private AnalyseServiceItf analyseServiceItfI;
    
    @Inject  private CatalogServiceItf catalogServiceItfI;
    
    @RequestMapping(value = "/content/classFile/{classFileId}", method = RequestMethod.GET, headers = "Accept=text/html")
    public String archiveContent(Model model, @PathVariable Long classFileId) {

    	ClassFile classFileDtoI = null;
    	
		try {
			classFileDtoI = catalogServiceItfI.getClassFile(classFileId);
			Collections.sort(classFileDtoI.getMethods(), new MethodMNComparator());
		} catch (Exception e) {
			e.printStackTrace();
		}
        model.addAttribute("classFile", classFileDtoI);     
        return "classFile/content/classFile";
    }


}
