package org.urbanizit.jscanner.analyser.scanner.utils;

import java.io.File;
import java.util.zip.ZipFile;

public abstract class FileUtils {

	public static void closeQuietly(ZipFile file) {
		if (file != null) {
			try {
				file.close();
			} catch (Exception e) {
				// Quiet...
			}
		}
	}

	public static void deleteQuietly(File file) {
		if (file != null) {
			try {
				file.delete();
			} catch (Exception e) {
				// Quiet...
			}
		}
	}
}
