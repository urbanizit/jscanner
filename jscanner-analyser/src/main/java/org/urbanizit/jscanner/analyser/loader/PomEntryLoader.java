package org.urbanizit.jscanner.analyser.loader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.ZipEntry;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.BuilderData;

/**
 * Read the pom.xml projet
 * 
 * @author ldassonville
 *
 */
public class PomEntryLoader extends AbstractArchiveEntryLoader{
	
	protected final String POM_FILE_NAME = "pom.xml";
	
	protected Logger logger = LoggerFactory.getLogger(PomEntryLoader.class);
	
	@Override
	public boolean isSupported(ZipEntry entry) {
		
		if(entry == null || entry.isDirectory()){
			return false;
		}		
		String name = entry.getName().toLowerCase();
		return name.endsWith(POM_FILE_NAME);
	}

	@Override
	protected void processLoading(String filename, InputStream is,	Archive archive) throws Exception {
		
		Reader reader = new InputStreamReader(is);
	    MavenXpp3Reader xpp3Reader = new MavenXpp3Reader();
	    Model model = xpp3Reader.read(reader);
	    
	    String groupId = model.getGroupId() == null ? model.getParent().getGroupId() : model.getGroupId();
	    String artifactId = model.getArtifactId();
	    String version = model.getVersion() == null ? model.getParent().getVersion() : model.getVersion();
	    archive.setBuilderData(new BuilderData("MAVEN", groupId, artifactId, version));	    
	}
}
