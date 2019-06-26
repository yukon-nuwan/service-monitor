/**
 * 
 */
package com.yukon.service.monitor.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Nuwan
 *
 */
public class CallerServiceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5993425169057432163L;
	
	private Long id;
	private Long callerId;
	private Long serviceId;
	private Integer pollingFrequency;
	private Integer graceTime;
	private ServiceDTO serviceDTO;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCallerId() {
		return callerId;
	}
	public void setCallerId(Long callerId) {
		this.callerId = callerId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getPollingFrequency() {
		return pollingFrequency;
	}
	public void setPollingFrequency(Integer pollingFrequency) {
		this.pollingFrequency = pollingFrequency;
	}
	public Integer getGraceTime() {
		return graceTime;
	}
	public void setGraceTime(Integer graceTime) {
		this.graceTime = graceTime;
	}
	public ServiceDTO getServiceDTO() {
		return serviceDTO;
	}
	public void setServiceDTO(ServiceDTO serviceDTO) {
		this.serviceDTO = serviceDTO;
	}
	
}
