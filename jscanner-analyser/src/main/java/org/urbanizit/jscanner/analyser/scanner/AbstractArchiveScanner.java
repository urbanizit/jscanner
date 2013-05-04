package org.urbanizit.jscanner.analyser.scanner;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.analyser.loader.AbstractArchiveEntryLoader;
import org.urbanizit.jscanner.core.utils.CheckSumType;
import org.urbanizit.jscanner.core.utils.CheckSumUtils;
import org.urbanizit.jscanner.core.utils.FileUtils;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.Location;


public abstract class AbstractArchiveScanner<T extends Archive>
{
	private final Logger logger = LoggerFactory.getLogger(AbstractArchiveScanner.class);

	protected abstract Collection<org.urbanizit.jscanner.analyser.loader.AbstractArchiveEntryLoader> getArchiveEntryLoaders();
	
	
	private void loadEntry(final ZipFile file, final ZipEntry entry, final Archive archive){
		if(!entry.isDirectory()){
			for (AbstractArchiveEntryLoader entryLoader : getArchiveEntryLoaders()) {
				if(entryLoader.isSupported(entry)){
					entryLoader.loadEntry(file, entry, archive);
					return;
				}
			}
		//	LOGGER.warn("No entry loader defined for {}",entry.getName());
		}
	}
	

   /**
    * Scan an archive
    * @param file The file
    * @return The archive
    */
   public T scan(File file){
      return scan(file, null, null, null);
   }
   

   /**
    * Scan an archive
    * @param file The file
    * @param gProvides The global provides map
    * @param known The set of known archives
    * @param blacklisted The set of black listed packages
    * @return The archive
    */
   public T scan(	File file, 
                  	Map<String, SortedSet<String>> gProvides, 
                    List<Archive> known, 
                    Set<String> blacklisted){
      T archive = null;
      ZipFile zipFile  = null;

      
      Long time = new Date().getTime();
      try {

         String name = file.getName();
         //String filename = file.getCanonicalPath();
         //TODO correction
         Location location = new Location();//(filename, null);       
         
         archive = getArchiveImpl(name, location);
         archive.setChecksum(CheckSumUtils.getCheckSum(file, CheckSumType.SHA256));
         zipFile = new ZipFile(file);
         Enumeration<? extends ZipEntry> zipEntryEnum = zipFile.entries();
   
         while (zipEntryEnum.hasMoreElements()){ 
        	 try{
        		 loadEntry(zipFile, (ZipEntry)zipEntryEnum.nextElement(), archive);
        	 }catch (Exception e) {
        		 logger.error("Error loading entry",e);
			}
         }  
      }catch (Exception e) {
    	  logger.error("Error scan file {}", file != null ? file.getName() : null, e);
      }finally{
    	 FileUtils.closeQuietly(zipFile);
      }   
      
     Long traitement =  new Date().getTime() - time;
     
     logger.debug("{} = {}", archive.getName(), traitement);
      return archive;
   }


	/**
	 * Give archive implementation.
	 * 
	 * @param name Archive name
	 * @param location Archive location
	 * @return Archive implementation
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T getArchiveImpl(String name, Location location)throws Exception {		
	     ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
	     Class<? extends Archive> classGeneric = (Class<? extends Archive>) parameterizedType.getActualTypeArguments()[0];	     
	     Object instance = classGeneric.getConstructor( String.class, Location.class ).newInstance(name,location);
	     return (T) instance;
	}
}
