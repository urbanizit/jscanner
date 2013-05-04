package org.urbanizit.jscanner.analyser.scanner;

import java.util.ArrayList;
import java.util.Collection;

import org.urbanizit.jscanner.analyser.loader.AbstractArchiveEntryLoader;
import org.urbanizit.jscanner.analyser.loader.ClassEntryLoader;
import org.urbanizit.jscanner.analyser.loader.ManifestEntryLoader;
import org.urbanizit.jscanner.analyser.loader.PomEntryLoader;
import org.urbanizit.jscanner.analyser.loader.SignEntryLoader;
import org.urbanizit.jscanner.transfert.JarArchive;


/**
 * JAR Scanner
 * @author ldassonville
 */
public class JARScanner extends AbstractArchiveScanner<JarArchive>{
	
	private final Collection<AbstractArchiveEntryLoader> archiveEntryLoaders;
	
	public JARScanner(){
		archiveEntryLoaders = new ArrayList<AbstractArchiveEntryLoader>();
	    archiveEntryLoaders.add(new ClassEntryLoader());
	    //archiveEntryLoaders.add(new ArchiveEntryLoader());
	    archiveEntryLoaders.add(new ManifestEntryLoader());
		archiveEntryLoaders.add(new SignEntryLoader());
		archiveEntryLoaders.add(new PomEntryLoader());
	}

	@Override
	protected Collection<AbstractArchiveEntryLoader> getArchiveEntryLoaders() {
		return archiveEntryLoaders;
	}
}
