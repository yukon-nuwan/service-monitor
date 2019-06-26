/**
 * 
 */
package com.yukon.service.monitor.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Nuwan
 *
 */

@Entity
@Table(name="service")
public class ServiceDomain extends BaseDomain{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2556197008290515558L;
	private String host;
	private Integer port;
	private LocalTime outageStart;
	private LocalTime outageEnd;
	private List<CallerServiceDomain> CallerServiceDomainList = new ArrayList<CallerServiceDomain>();
	
	
	@Column(name="host")
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	@Column(name="port")
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	@Column(name="outage_start")
	public LocalTime getOutageStart() {
		return outageStart;
	}
	public void setOutageStart(LocalTime outageStart) {
		this.outageStart = outageStart;
	}
	
	@Column(name="outage_end")
	public LocalTime getOutageEnd() {
		return outageEnd;
	}
	public void setOutageEnd(LocalTime outageEnd) {
		this.outageEnd = outageEnd;
	}
	
	
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
	public List<CallerServiceDomain> getCallerServiceDomainList() {
		return CallerServiceDomainList;
	}
	public void setCallerServiceDomainList(List<CallerServiceDomain> callerServiceDomainList) {
		CallerServiceDomainList = callerServiceDomainList;
	}
	
	
	
	

}
