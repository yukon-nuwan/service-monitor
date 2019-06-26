/**
 * 
 */
package com.yukon.service.monitor.service;

import java.util.List;

import com.yukon.service.monitor.dto.CallerDTO;

/**
 * @author Nuwan
 *
 */
public interface CallerService {
	
	/**
	 * Save caller
	 * @param callerDTO
	 * @return
	 */
	public CallerDTO save(CallerDTO callerDTO);

	
	/**
	 * Update caller by caller id
	 * @param callerDetailDTO
	 * @return
	 */
	public CallerDTO updateCallerByCallerId(CallerDTO callerDTO);

	
	/**
	 * Delete caller by caller id
	 * @param id
	 * @return
	 */
	public Boolean deleteCallerByCallerId(Long id);

	
	/**
	 * Get caller by caller id
	 * @param id
	 * @return
	 */
	public CallerDTO getCallerByCallerId(Long id);


	/**
	 * Run caller assign services to check the status
	 * @param id
	 */
	public void runCallerServices(Long id);


	/**
	 * Get all callers
	 * @return
	 */
	public List<CallerDTO> getAllCallers();


}
