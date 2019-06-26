/**
 * 
 */
package com.yukon.service.monitor.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author Nuwan
 *
 */
public class ServiceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5993425169057432163L;
	
	private Long id;
	private String host;
	private Integer port;
	private LocalTime outageStart;
	private LocalTime outageEnd;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public LocalTime getOutageStart() {
		return outageStart;
	}
	public void setOutageStart(LocalTime outageStart) {
		this.outageStart = outageStart;
	}
	public LocalTime getOutageEnd() {
		return outageEnd;
	}
	public void setOutageEnd(LocalTime outageEnd) {
		this.outageEnd = outageEnd;
	}

	

}
