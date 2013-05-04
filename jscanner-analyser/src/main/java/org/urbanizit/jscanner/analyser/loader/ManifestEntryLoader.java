package org.urbanizit.jscanner.analyser.loader;

import java.io.InputStream;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.transfert.Archive;


/**
 * Classe d'extraction de données d'un Manifest à partir d'une flux InputStream.
 * @author ldassonville
 */
public class ManifestEntryLoader extends AbstractArchiveEntryLoader {

	private Logger logger = LoggerFactory.getLogger(ManifestEntryLoader.class);
	
	private static final String MANIFEST_DIRECTORY = "META-INF";
	
	private static final String MANIFEST_EXTENSION = ".MF";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSupported(final ZipEntry entry) {
		if(entry == null || entry.isDirectory()){
			return false;
		}		
		String entryName = entry.getName().toUpperCase();		
		
		return entryName.indexOf(MANIFEST_DIRECTORY) != -1 
					&& entryName.endsWith(MANIFEST_EXTENSION);
       		
	}
	/**
	 * {@inheritDoc}
	 */
	protected void processLoading(final String fileName, final InputStream is, final Archive archive)throws Exception {
		//TODO correct it
		//archive.setManifest(readManifest(is));		
	}
	   
	private SortedMap<String, String> loadAttributes(Attributes attributes ){
		
		 SortedMap<String, String> lManifest = new TreeMap<String, String>();
		  if(attributes != null){
			   Set<Entry<Object, Object>> t=attributes.entrySet();
			   for (Entry<Object, Object> entry : t) {
				   lManifest.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
			   }
		  }
		  return lManifest;
	}
	
	private void printAttributes(Attributes attributes ){
		  if(attributes != null){
			   Set<Entry<Object, Object>> t=attributes.entrySet();
			   for (Entry<Object, Object> entry2 : t) {		
				   logger.debug("sub-Entry => {} = {} ", entry2.getKey(), entry2.getValue());
			   }
		  }
	}
	
	private SortedMap<String, String> readManifest (InputStream  inputStream)throws Exception {
	      
	       Manifest manifest = new Manifest(inputStream);
	       SortedMap<String, String> lManifest = loadAttributes(manifest.getMainAttributes());
	       return lManifest;
	       
	      // printAttributes(manifest.getMainAttributes());
	    /*   
	       
	      Map<String, Attributes> entries = manifest.getEntries();
	      
	      
	      if (entries != null){	    	   
	    	   for (Map.Entry<String, Attributes> entry : entries.entrySet()) {
	    	   	   logger.debug("Entry {}", entry.getKey()); 	   
	    		  
	    		   Attributes attributes = entry.getValue();	    		   
	    		   printAttributes(attributes);
			 }
	      }
	    */	      
	    	/**   
	    	   lManifest.putAll(manifest.getEntries());
	    	   
	    	   Attributes 
	    	   
	          Attributes mainAttributes = manifest.getMainAttributes();
	          version = mainAttributes.getValue("Specification-Version");
	          if (version == null)
	             version = mainAttributes.getValue("Implementation-Version");
	          if (version == null)
	             version = mainAttributes.getValue("Version");

	          if (version == null && manifest.getEntries() != null)
	          {
	             Iterator<?> ait = manifest.getEntries().values().iterator();
	             while (version == null && ait.hasNext())
	             {
	                Attributes attributes = (Attributes)ait.next();

	                version = attributes.getValue("Specification-Version");
	                if (version == null)
	                   version = attributes.getValue("Implementation-Version");
	                if (version == null)
	                   version = attributes.getValue("Version");
	             }
	          }

	          
	       }*/
		}


}
