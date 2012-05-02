package org.urbanizit.jscanner.analyser.scanner;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryScanner extends AbstractDirectoryScanner{

	private Logger LOGGER = LoggerFactory.getLogger(DirectoryScanner.class);
	
	protected final static String JOKER_TOKEN = "*";
	protected final static String SEPARATOR_TOKEN = ",";
	
	/** Archives types that should be scanned */
	private Set<String> archives = null;

	/**
	 * Constructor
	 */
	public DirectoryScanner() {
		archives = new HashSet<String>();
		resetDefaultEntries();
	}
	
	public void resetDefaultEntries() {
		archives.clear();
		archives.add(".jar");
		archives.add(".ear");
		archives.add(".war");
	}
	
	/**
	 * Set archives
	 * 
	 * @param scan
	 *            The archives
	 */
	public void setArchivesFiler(String scan) {
		archives.clear();

		if (scan != null) {
			StringTokenizer st = new StringTokenizer(scan,  SEPARATOR_TOKEN);
			while (st.hasMoreTokens()) {
				String token = st.nextToken();

				if (token.startsWith(JOKER_TOKEN)) {
					token = token.substring(JOKER_TOKEN.length());
				}
				archives.add(token.toLowerCase());
			}
		}

		if (archives.isEmpty()) {
			resetDefaultEntries();
		}
	}


	@Override
	protected boolean isAccepted(File file) {
		if(file == null){
			return false;
		}
		
		if(file.isDirectory()){
			return true;
		}
		
		int extensionPosition = file.getName().lastIndexOf(".");
		String extension = null;
        if (extensionPosition != -1)
            extension = file.getName().substring(file.getName().lastIndexOf("."));
        
        return archives.contains(extension);
	}

	@Override
	public List<File> scan(File file) {
		return super.scanContent(file);
	}
}
