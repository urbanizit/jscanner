package org.urbanizit.jscanner.transfert;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Location implements Serializable {

	private static final long serialVersionUID = 7250244678157929200L;

	
    public Location(){}
    
 
	@Override
	public String toString() {
		return  new ToStringBuilder(this)
		.toString();
	}
}
