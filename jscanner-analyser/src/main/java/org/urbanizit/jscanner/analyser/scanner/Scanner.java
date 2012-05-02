package org.urbanizit.jscanner.analyser.scanner;

import java.io.File;
import java.util.List;

public interface Scanner {

	/**
	 * Scan a directory
	 * @param file
	 * @return
	 */
	List<File> scan(final File file);
}
