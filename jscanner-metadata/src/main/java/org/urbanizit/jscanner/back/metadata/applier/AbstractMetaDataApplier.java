package org.urbanizit.jscanner.back.metadata.applier;

public abstract class AbstractMetaDataApplier<T> implements MetaDataApplier<T> {

	public String getName(){
		return getClass().getSimpleName();
	}
	
	public abstract void apply(T data);
	
}
