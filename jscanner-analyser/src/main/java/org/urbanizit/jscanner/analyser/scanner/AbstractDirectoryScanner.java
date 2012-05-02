package org.urbanizit.jscanner.analyser.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDirectoryScanner implements Scanner{

	protected abstract boolean isAccepted(final File file);

	protected List<File> scanContent(File file) {
		
		List<File> result = new ArrayList<File>();
		if(isAccepted(file)){
			if (file.isDirectory()) {
				
				File[] subFiles = file.listFiles();
				if(subFiles != null){
					for (File subFile : subFiles) {
						result.addAll(scanContent(subFile));
					}		
				}
			} else if (file.isFile()) {
				result.add(file);
			} 	
		}		
		return result;
	}
}
