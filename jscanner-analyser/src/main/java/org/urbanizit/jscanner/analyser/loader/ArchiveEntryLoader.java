package org.urbanizit.jscanner.analyser.loader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.urbanizit.jscanner.ArchiveTypes;
import org.urbanizit.jscanner.analyser.scanner.AbstractArchiveScanner;
import org.urbanizit.jscanner.analyser.scanner.factory.ScannerFactory;
import org.urbanizit.jscanner.analyser.scanner.utils.FileUtils;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.NestableArchive;


public class ArchiveEntryLoader extends AbstractArchiveEntryLoader {
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSupported(final ZipEntry entry) {
		if(entry == null || entry.isDirectory()){
			return false;
		}		
		
		return ArchiveTypes.getArchiveType(entry.getName()) != null;   		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void processLoading(final String filename, final InputStream is, final Archive archive)throws Exception {

		NestableArchive nestableArchive = (NestableArchive) archive;	
		if(nestableArchive.getSubArchives() == null){
			nestableArchive.setSubArchives(new ArrayList<Archive>());
		}
		nestableArchive.getSubArchives().add(readArchive(filename, is));		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void checkLoadingParams(final ZipFile file, final ZipEntry entry, final Archive archive ){
		super.checkLoadingParams(file, entry, archive);
		
		if(!(archive instanceof NestableArchive)){
			throw new IllegalArgumentException("NestableArchive required");
		}		
	}
	
	/**
	 * Read archive file entry.
	 * 
	 * @param name entry name
	 * @param is entry Stream
	 * @return Archive read
	 * @throws Exception
	 */
	protected Archive readArchive(final String name, final InputStream is)throws Exception{
		
		Integer archiveType = ArchiveTypes.getArchiveType(name);
		AbstractArchiveScanner<? extends Archive> subScanner = ScannerFactory.getArchiveScanner(archiveType);
		
		File file = null, filePath = null;
		FileOutputStream os = null;
		String path = "./tmp"+Math.random()+"/";
		try {
			
			//Create work directory
			filePath = new File(path);
			filePath.mkdirs();
			
			file = new File(path+extractFileName(name));
			os = new FileOutputStream(file);			
			IOUtils.copy(is, os);
			return subScanner.scan(file);
		} finally {
			IOUtils.closeQuietly(os);
			FileUtils.deleteQuietly(file);
			FileUtils.deleteQuietly(filePath);			
		} 
	}
}
