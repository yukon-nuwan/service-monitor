/**
 * 
 */
package com.yukon.service.monitor.dto;

import java.io.Serializable;

/**
 * @author Nuwan
 *
 */
public class ResponseDTO <T> implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2688715204856194640L;
	private T result;
	private String status;
	private String description;
	
	
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
