/**
 * 
 */
package com.yukon.service.monitor.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Nuwan
 *
 */

@Entity
@Table(name="caller")
public class CallerDomain extends BaseDomain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2000224394197724706L;
	private String name;
	private List<CallerServiceDomain> CallerServiceDomainList = new ArrayList<CallerServiceDomain>();
	
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "caller", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<CallerServiceDomain> getCallerServiceDomainList() {
		return CallerServiceDomainList;
	}
	public void setCallerServiceDomainList(List<CallerServiceDomain> callerServiceDomainList) {
		CallerServiceDomainList = callerServiceDomainList;
	}

}
