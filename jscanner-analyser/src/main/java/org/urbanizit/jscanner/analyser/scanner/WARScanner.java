package org.urbanizit.jscanner.analyser.scanner;

import java.util.ArrayList;
import java.util.Collection;

import org.urbanizit.jscanner.analyser.loader.AbstractArchiveEntryLoader;
import org.urbanizit.jscanner.analyser.loader.ArchiveEntryLoader;
import org.urbanizit.jscanner.analyser.loader.ClassEntryLoader;
import org.urbanizit.jscanner.analyser.loader.ManifestEntryLoader;
import org.urbanizit.jscanner.analyser.loader.SignEntryLoader;
import org.urbanizit.jscanner.transfert.WarArchive;



/**
 * WAR Scanner
 * @author ldassonville
 */
public class WARScanner extends AbstractArchiveScanner<WarArchive>{

	private final Collection<AbstractArchiveEntryLoader> archiveEntryLoaders;
	
	public WARScanner(){
		archiveEntryLoaders = new ArrayList<AbstractArchiveEntryLoader>();
	    archiveEntryLoaders.add(new ClassEntryLoader());
	    archiveEntryLoaders.add(new ArchiveEntryLoader());
	    archiveEntryLoaders.add(new ManifestEntryLoader());
		archiveEntryLoaders.add(new SignEntryLoader());
	}

	@Override
	protected Collection<AbstractArchiveEntryLoader> getArchiveEntryLoaders() {
		return archiveEntryLoaders;
	}
}
