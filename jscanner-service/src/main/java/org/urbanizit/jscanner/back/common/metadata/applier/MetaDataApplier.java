package org.urbanizit.jscanner.back.common.metadata.applier;

public interface MetaDataApplier<T> {

	String getName();
	
	void apply(T data);
	
}
