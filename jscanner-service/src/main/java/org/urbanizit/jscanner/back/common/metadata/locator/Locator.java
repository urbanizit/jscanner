package org.urbanizit.jscanner.back.common.metadata.locator;

import java.util.List;

public interface Locator<T> {

	public List<T> locate();
}
