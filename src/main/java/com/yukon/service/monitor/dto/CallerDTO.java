/**
 * 
 */
package com.yukon.service.monitor.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nuwan
 *
 */
public class CallerDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7189882289547988039L;
	
	private Long id;
	private String name;
	private List<CallerServiceDTO> callerServiceDTOList = new ArrayList<CallerServiceDTO>();
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<CallerServiceDTO> getCallerServiceDTOList() {
		return callerServiceDTOList;
	}
	public void setCallerServiceDTOList(List<CallerServiceDTO> callerServiceDTOList) {
		this.callerServiceDTOList = callerServiceDTOList;
	}
	
	
	
	
}
