/**
 * 
 */
package com.yukon.service.monitor.service;

import java.util.List;

import com.yukon.service.monitor.dto.CallerServiceDTO;
import com.yukon.service.monitor.dto.ServiceMessageDTO;

/**
 * @author Nuwan
 *
 */
public interface CallerServiceService {




	/**
	 * Save callers' service
	 * @param CallerServiceDTO
	 * @return
	 */
	public CallerServiceDTO save(CallerServiceDTO callerServiceDTO);
	
	
	/**
	 * Update callers' service
	 * @param CallerServiceDTO
	 * @return
	 */
	public CallerServiceDTO update(CallerServiceDTO callerServiceDTO);


	/**
	 * Delete caller service by caller
	 * @param callerId
	 * @param serviceId
	 * @return
	 */
	public Boolean deleteCallerService(Long callerId, Long serviceId);
	
	
	/**
	 * Find all caller service
	 * @return
	 */
	public List<CallerServiceDTO> findAlCallerService();
	
	
	/**
	 * Run all caller services task
	 */
	public ServiceMessageDTO runCallerServices(CallerServiceDTO callerServiceDTO);

}
