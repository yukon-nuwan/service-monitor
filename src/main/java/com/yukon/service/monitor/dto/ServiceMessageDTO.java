/**
 * 
 */
package com.yukon.service.monitor.dto;

import java.io.Serializable;

/**
 * @author Nuwan
 *
 */

public class ServiceMessageDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7103439532113094749L;
	private Boolean isAlive;
	private String message;
	private Long callerId;
	private Long serviceId;
	private String host;
	private Integer port;
	
	
	public Boolean getIsAlive() {
		return isAlive;
	}
	public void setIsAlive(Boolean isAlive) {
		this.isAlive = isAlive;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	

}
