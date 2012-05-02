package org.urbanizit.jscanner.analyser.loader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

import org.urbanizit.jscanner.transfert.Archive;


public class SignEntryLoader extends AbstractArchiveEntryLoader {

	private static final String SIGN_DIRECTORY = "META-INF";
	
	private static final String SIGN_EXTENSION = ".SF";
	
	@Override
	public boolean isSupported(final ZipEntry entry) {
		if(entry == null || entry.isDirectory()){
			return false;
		}		
		String entryName = entry.getName().toUpperCase();		
		
		return entryName.indexOf(SIGN_DIRECTORY) != -1 
					&& entryName.endsWith(SIGN_EXTENSION);
       		
	}

	@Override
	protected void processLoading(final String fileName, final InputStream is, final Archive archive)throws Exception {
		archive.setSigns(readSigns(is));		
	}
	   
	private List<String> readSigns(InputStream  inputStream)throws Exception {
		
		List<String> signs = new ArrayList<String>();
		InputStreamReader isr = new InputStreamReader(inputStream);
		LineNumberReader lnr = new LineNumberReader(isr);
		
		String s = lnr.readLine();
		while (s != null) {
			signs.add(s);
			s = lnr.readLine();
		}
		return signs;
	}

}
