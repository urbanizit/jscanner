package org.urbanizit.jscanner.analyser.resolver;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.urbanizit.jscanner.analyser.scanner.utils.ArchiveNameUtils;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.EarArchive;
import org.urbanizit.jscanner.transfert.WarArchive;

public class OwnerGroupResolver {
		
	private Collection<OwnerRules> ownerRules = new ArrayList<OwnerRules>();
	
	private static final OwnerGroupResolver singleton = new OwnerGroupResolver();
	
	private OwnerGroupResolver(){
		loadRules();		
	};
	
    public static String resolveOwner(final Archive archive){
    	
    	//Nom de fichier sans version
    	if(archive instanceof EarArchive || archive instanceof WarArchive){    		
    		String filename =  archive.getName();
    		return ArchiveNameUtils.getComponentBaseName(filename)+"."+ArchiveNameUtils.getExtension(filename);
    	}
    	
      	if(archive == null || !Boolean.TRUE.equals(archive.getCompagnyFile())){
    		return null;
    	}
    	    	
    	String resolverOwner = singleton.resolveArchiveOwner(archive.getName());
    	return resolverOwner;
    }
    
    private void loadRules(){
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("owner-rules");
    	
    	Enumeration<String> keys = bundle.getKeys();

		while (keys.hasMoreElements()) {			
			String key = keys.nextElement(); 
			
			if(key.contains("regexp")){
				String keyName= key.split("\\.")[0];				
				ownerRules.add(new OwnerRules(bundle.getString(keyName+".regexp"), bundle.getString(keyName+".owner")));
			}			
		}    	
    }
    
    private String resolveArchiveOwner(String archiveName){
    	if(archiveName == null){
    		return null;
    	}
    	
    	for (OwnerRules ownerRule : ownerRules) {
    		if(ownerRule.isMatch(archiveName)){
    			return ownerRule.getComponent();
    		}
    	}    	
    	return null;
    }
    
    private class OwnerRules{
    	
    	private Pattern pattern;
    	private String component;
    	
    	private OwnerRules(String regExp, String component){
    		this.pattern = Pattern.compile(regExp);
    		this.component = component;
    	}
    
    	private boolean isMatch(String filename){    		
    		return pattern.matcher(filename).matches();
    	}

		public String getComponent() {
			return component;
		}
    }
}