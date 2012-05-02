package org.urbanizit.jscanner.back.common.resolver;

import java.util.Collection;


public interface ResolverDataSource<T, D> {

	 D save(final T data);
	 
	 Collection<D> findExisting(final Collection<T> data);	
	 
}
