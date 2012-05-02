package org.urbanizit.jscanner.back.persistence.bo;

import java.io.Serializable;


public interface EntityItf<ID extends Serializable> extends Serializable {

	ID getId();

	void setId(ID id);
}
