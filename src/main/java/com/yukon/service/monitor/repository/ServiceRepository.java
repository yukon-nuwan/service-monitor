/**
 * 
 */
package com.yukon.service.monitor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yukon.service.monitor.domain.ServiceDomain;

/**
 * @author Nuwan
 *
 */

@Repository
public interface ServiceRepository extends JpaRepository<ServiceDomain, Long>{
	
	
	/**
	 * Find service by host and port
	 * @param host
	 * @param port
	 * @return
	 */
	Optional<ServiceDomain> findByHostAndPort(String host,Integer port);
	

}
