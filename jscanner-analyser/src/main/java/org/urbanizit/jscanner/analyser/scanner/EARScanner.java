package org.urbanizit.jscanner.analyser.scanner;

import java.util.ArrayList;
import java.util.Collection;

import org.urbanizit.jscanner.analyser.loader.AbstractArchiveEntryLoader;
import org.urbanizit.jscanner.analyser.loader.ApplicationXMLEntryLoader;
import org.urbanizit.jscanner.analyser.loader.ArchiveEntryLoader;
import org.urbanizit.jscanner.analyser.loader.ManifestEntryLoader;
import org.urbanizit.jscanner.analyser.loader.SignEntryLoader;
import org.urbanizit.jscanner.transfert.EarArchive;


/**
 * EAR Scanner
 * @author ldassonville
 */
public class EARScanner extends AbstractArchiveScanner<EarArchive>{
		
	private final Collection<AbstractArchiveEntryLoader> archiveEntryLoaders;
	
	public EARScanner(){
		archiveEntryLoaders = new ArrayList<AbstractArchiveEntryLoader>();
	    archiveEntryLoaders.add(new ArchiveEntryLoader());
	    archiveEntryLoaders.add(new ManifestEntryLoader());
	    archiveEntryLoaders.add(new ApplicationXMLEntryLoader());
		archiveEntryLoaders.add(new SignEntryLoader());
	}

	@Override
	protected Collection<AbstractArchiveEntryLoader> getArchiveEntryLoaders() {
		return archiveEntryLoaders;
	}
}
