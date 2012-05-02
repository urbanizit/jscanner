package org.urbanizit.jscanner.back.common.extractor;

import java.util.Collection;

public interface DataExtractor<D, T> {

	void extractData(D obj, Collection<T> accumulator);
	
}
