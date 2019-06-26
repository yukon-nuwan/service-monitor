/**
 * 
 */
package com.yukon.service.monitor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yukon.service.monitor.domain.CallerDomain;

/**
 * @author Nuwan
 *
 */

@Repository
public interface CallerRepository extends JpaRepository<CallerDomain, Long>{

	/**
	 * Find caller service by name
	 * @param name
	 * @return
	 */
	Optional<CallerDomain> findCallerByName(String name);
	

}
