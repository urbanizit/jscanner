package org.urbanizit.jscanner.analyser.loader;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.transfert.Archive;


/**
 * Abstract class for zip entry Loading.
 * @author ldassonville
 */
public abstract class AbstractArchiveEntryLoader {

	protected static final String ENTRY_SEPARATOR = "/";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractArchiveEntryLoader.class);
	
	/**
	 * Is the entry supported by loader
	 * @param entry Entry
	 * @return true supported / false non Supported
	 */
	public abstract boolean isSupported( final ZipEntry entry);
		
	/**
	 * Process to the entry loading.
	 * @param filename File name
	 * @param is File Byte Stream
	 * @param archive Source archive
	 * @throws Exception
	 */
	protected abstract void processLoading(final String filename, final InputStream is, final Archive archive) throws Exception;

	/**
	 * Load entry.
	 * @param file Original ZipFile 
	 * @param entry ZIP file entry
	 * @param archive
	 */
	public void loadEntry(final ZipFile file, final ZipEntry entry, final Archive archive ){
		checkLoadingParams(file, entry, archive);		
		
		InputStream is = null;
		try{
			 processLoading(entry.getName(), file.getInputStream(entry), archive);	           
		}catch (Exception e) {
			LOGGER.error("Error loading entry", e);
		}finally{
			IOUtils.closeQuietly(is);
		}
	}

	/**
	 * Check parameters validity.
	 * @param file ZipFile
	 * @param entry ZipFile entry
	 * @param archive Archive
	 */
	protected void checkLoadingParams(final ZipFile file, final ZipEntry entry, final Archive archive ){
		if(!isSupported(entry)){
			throw new IllegalArgumentException("Unsupported entry");
		}		
		if(archive == null){
			throw new IllegalArgumentException("Invalid archive");
		}			
		if(file == null){
			throw new IllegalArgumentException("Invalid zipFile");
		}
	}
	
	/**
	 * Extration du package a partir du className.
	 * @param className
	 * @return package
	 */
	protected String extractFileName(final String className) {		
		int pathIdx = className.lastIndexOf(ENTRY_SEPARATOR);
		if (pathIdx != -1) {
			return className.substring(pathIdx+1);
		}
		return className;
	}
}
