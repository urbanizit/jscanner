package org.urbanizit.jscanner.transfert;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ArchiveDependency implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long providerId;
	private Long customerId;

	public ArchiveDependency(){}
	
	public ArchiveDependency(Long providerId, Long customerId){
		this.providerId = providerId;
		this.customerId = customerId;		
	}
			
	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return  new ToStringBuilder(ArchiveDependency.class)
		.append(providerId)
		.append("<--")
		.append(customerId)
		.toString();
	}
}
