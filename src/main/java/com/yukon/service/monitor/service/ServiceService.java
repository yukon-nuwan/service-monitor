/**
 * 
 */
package com.yukon.service.monitor.service;

import java.util.List;

import com.yukon.service.monitor.dto.ServiceDTO;

/**
 * @author Nuwan
 *
 */
public interface ServiceService {
	
	/**
	 * Save service
	 * @param serviceDTO
	 * @return
	 */
	public ServiceDTO save(ServiceDTO serviceDTO);

	
	/**
	 * Update service by service id
	 * @param serviceDetailDTO
	 * @return
	 */
	public ServiceDTO updateServiceByServiceId(ServiceDTO serviceDTO);

	
	/**
	 * Delete service by service id
	 * @param id
	 * @return
	 */
	public Boolean deleteServiceByServiceId(Long id);

	
	/**
	 * Get service by service id
	 * @param id
	 * @return
	 */
	public ServiceDTO getServiceByServiceId(Long id);


	/**
	 * Get All Service
	 * @return
	 */
	public List<ServiceDTO> getAllService();


}
