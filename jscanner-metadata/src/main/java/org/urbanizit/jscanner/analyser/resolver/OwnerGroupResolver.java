package org.urbanizit.jscanner.analyser.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.EarArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.WarArchiveBo;
import org.urbanizit.jscanner.core.utils.ArchiveNameUtils;

public class OwnerGroupResolver {
		
	private Collection<OwnerRules> ownerRules = new ArrayList<OwnerRules>();
	
	private static final OwnerGroupResolver singleton = new OwnerGroupResolver();
	
	private OwnerGroupResolver(){
		loadRules();		
	};
	
    public static String resolveOwner(final ArchiveBo archive){
    	
    	//Nom de fichier sans version
    	if(archive instanceof EarArchiveBo || archive instanceof WarArchiveBo){    		
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