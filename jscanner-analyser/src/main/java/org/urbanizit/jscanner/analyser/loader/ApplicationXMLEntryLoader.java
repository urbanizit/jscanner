package org.urbanizit.jscanner.analyser.loader;

import java.io.InputStream;
import java.util.zip.ZipEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.transfert.Archive;


/**
 * @author ldassonville
 */
public class ApplicationXMLEntryLoader extends AbstractArchiveEntryLoader {
	
	private Logger logger = LoggerFactory.getLogger(ApplicationXMLEntryLoader.class);
	
	private static final String APPLICATION_XML_DIRECTORY = "META-INF";
	
	private static final String APPLICATION_XML_NAME = "application.xml";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSupported(final ZipEntry entry) {
		if(entry == null || entry.isDirectory()){
			return false;
		}		
		String entryName = entry.getName();		
		
		return entryName.indexOf(APPLICATION_XML_DIRECTORY) != -1 
					&& entryName.endsWith(APPLICATION_XML_NAME);
       		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void processLoading(final String fileName, final InputStream is, final Archive archive)throws Exception {
		//TODO processLoading Implementation
		logger.debug("Reading : {}", fileName);
	}
	   

	/**
	 * Read application.xml entry file.
	 * @param inputStream
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void readApplicationXML(InputStream inputStream)throws Exception {
		//TODO readApplicationXML Implementation
	}

}
