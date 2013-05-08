package org.urbanizit.jscanner.front.utils.comparator;

import java.util.Comparator;

import org.urbanizit.jscanner.transfert.Archive;


/**
 * @author ldassonville
 *
 */
public class ArchiveNameComparator implements Comparator<Archive>{

	@Override
	public int compare(Archive o1, Archive o2) {
		if(o2 == null && o1 == null)return 0;
		if(o1 == null)return -1;
		if(o2 == null)return 1;
		
		String archiveName1 = o1.getName();
		String archiveName2 = o2.getName();
		if(archiveName1 == null && archiveName2 == null)return 0;
		if(archiveName1 == null)return -1;
		if(archiveName2 == null)return 1;
		
		return archiveName1.compareToIgnoreCase(archiveName2);
	}

}
