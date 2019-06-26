/**
 * 
 */
package com.yukon.service.monitor.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yukon.service.monitor.domain.CallerServiceDomain;

/**
 * @author Nuwan
 *
 */

@Repository
public interface CallerServiceRepository extends JpaRepository<CallerServiceDomain, Long>{
	
	/**
	 * Find caller service by caller id and service id
	 * @param callerId
	 * @param serviceId
	 * @return
	 */
	@Query("SELECT callerService FROM CallerServiceDomain callerService  join  callerService.caller caller join  callerService.service  service "
			+ "WHERE caller.id = ?1 AND service.id = ?2")
	Optional<CallerServiceDomain>  findByCallerAndService(Long callerId, Long serviceId);
	
	
	/**
	 * Delete caller service by id
	 * @param callerServiceId
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM CallerServiceDomain callerService WHERE callerService.id = ?1")
	void deleteCallerServiceById(Long callerServiceId);

	

}
