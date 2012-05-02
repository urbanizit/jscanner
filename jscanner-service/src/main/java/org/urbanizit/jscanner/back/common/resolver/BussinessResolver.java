package org.urbanizit.jscanner.back.common.resolver;

import java.util.Map;

public interface BussinessResolver<A, T, D> {

	Map<T, D> resolve(final A element);
	
}
