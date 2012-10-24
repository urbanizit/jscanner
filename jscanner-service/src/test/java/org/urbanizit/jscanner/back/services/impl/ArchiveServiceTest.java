package org.urbanizit.jscanner.back.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.urbanizit.jscanner.ArchiveTypes;
import org.urbanizit.jscanner.analyser.scanner.AbstractArchiveScanner;
import org.urbanizit.jscanner.analyser.scanner.DirectoryScanner;
import org.urbanizit.jscanner.analyser.scanner.factory.ScannerFactory;
import org.urbanizit.jscanner.core.utils.CheckSumUtils;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ArchiveCriteria;
import org.urbanizit.jscanner.transfert.itf.ArchiveServiceItf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/jscannerContext.xml"})
public class ArchiveServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveServiceTest.class);
	
	@Inject private ArchiveServiceItf archiveServiceItfI;
    
    @Test
    public void saveArchiveTest(){
    	

    	//	List<File> files = DirectoryScanner.scan(new File("C:\\Programmation\\jcanner-tests\\full-libs\\"));
    	List<File> files = new DirectoryScanner().scan(new File("K:\\Programmation\\appdata\\jscanner_data\\"));
    	
    	
    	//List<File> files = new ArrayList<File>();

    	//List<File> files = new DirectoryScanner().scan(new File("C:\\jscanner-tests.light\\"));
    	
    	try {
	    	Collection<Callable<Long>> registerTasks = new ArrayList<Callable<Long>>();
	    	for (File file : files) {   		
	    		registerTasks.add(new RegisterArchive(file, archiveServiceItfI));
			}  	
	   	
	    	ExecutorService executorService = Executors.newFixedThreadPool(1);
			executorService.invokeAll(registerTasks);		    	
			executorService.shutdown();
    	} catch (InterruptedException e) {
    		LOGGER.error("Error saveArchiveTest",e);
		}    
    }     
    
    private class RegisterArchive implements Callable<Long>{
   
    	private ArchiveServiceItf archiveServiceItfI;
    	private File file;
    	
    	public RegisterArchive(File file, ArchiveServiceItf archiveServiceItfI){
    		this.file = file;
    		this.archiveServiceItfI = archiveServiceItfI;
    	}
    	
		@Override
		public Long call() throws Exception {
			
			try{
				System.out.println("start task "+file.getName());
				
				String checksum = CheckSumUtils.getSha256(new FileInputStream(file));
				ArchiveCriteria criteria = new ArchiveCriteria();
				criteria.setChecksum(checksum);				
				List<Archive> archiveDtoIs = archiveServiceItfI.findArchiveByCriteria(criteria);
				
				if(archiveDtoIs == null || archiveDtoIs.isEmpty()){				
					Integer archiveType = ArchiveTypes.getArchiveType(file.getName());				
		    		AbstractArchiveScanner<? extends Archive> scanner = ScannerFactory.getArchiveScanner(archiveType);    		
		    		Archive archiveDtoI  = scanner.scan(file);   		
		        	return archiveServiceItfI.registerArchive(archiveDtoI);
				}else{
					return archiveDtoIs.get(0).getId();
				}
			}catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
    }
}
