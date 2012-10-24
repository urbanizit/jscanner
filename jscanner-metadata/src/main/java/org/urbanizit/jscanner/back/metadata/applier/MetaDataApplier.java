package org.urbanizit.jscanner.back.metadata.applier;

public interface MetaDataApplier<T> {

	String getName();
	
	void apply(T data);
	
}
