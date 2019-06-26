/**
 * 
 */
package com.yukon.service.monitor.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Nuwan
 *
 */

@MappedSuperclass
public class BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6813893474007886624L;
	
	private Long id;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	} 

	

}
