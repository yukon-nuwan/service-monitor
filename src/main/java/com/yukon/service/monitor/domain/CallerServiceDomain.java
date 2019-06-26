/**
 * 
 */
package com.yukon.service.monitor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Nuwan
 *
 */

@Entity
@Table(name="caller_service")
public class CallerServiceDomain extends BaseDomain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4009741643576003349L;
	private CallerDomain caller;
	private ServiceDomain service;
	private Integer pollingFrequency;
	private Integer graceTime;
	
		
	
	@ManyToOne
    @JoinColumn
	public CallerDomain getCaller() {
		return caller;
	}
	public void setCaller(CallerDomain caller) {
		this.caller = caller;
	}

	@ManyToOne
    @JoinColumn
	public ServiceDomain getService() {
		return service;
	}
	public void setService(ServiceDomain service) {
		this.service = service;
	}
	
	@NotNull
	@Min(value = 1)
	@Column(name="polling_frequency")
	public Integer getPollingFrequency() {
		return pollingFrequency;
	}
	public void setPollingFrequency(Integer pollingFrequency) {
		this.pollingFrequency = pollingFrequency;
	}
	
	@Column(name="grace_time")
	public Integer getGraceTime() {
		return graceTime;
	}
	public void setGraceTime(Integer graceTime) {
		this.graceTime = graceTime;
	}
	
	
	
	
}
