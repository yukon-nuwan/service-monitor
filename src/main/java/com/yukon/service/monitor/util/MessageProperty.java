/**
 * 
 */
package com.yukon.service.monitor.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Nuwan
 *
 */

@Configuration
@PropertySource("classpath:commonMessage.properties")
public class MessageProperty {
	
	@Value("${common.message.recordNotFound}")
	private String recordNotFound;
	
	@Value("${common.message.recordExist}")
	private String recordExist;
	
	@Value("${common.message.save}")
	private String save;
	
	@Value("${common.message.update}")
	private String update;
	
	@Value("${common.message.delete}")
	private String delete;
	
	@Value("${common.message.recordFound}")
	private String recordFound;

	
	
	public String getRecordNotFound() {
		return recordNotFound;
	}

	public void setRecordNotFound(String recordNotFound) { 
		this.recordNotFound = recordNotFound;
	}

	public String getRecordExist() {
		return recordExist;
	}

	public void setRecordExist(String recordExist) {
		this.recordExist = recordExist;
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = save;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getRecordFound() {
		return recordFound;
	}

	public void setRecordFound(String recordFound) {
		this.recordFound = recordFound;
	}

}
