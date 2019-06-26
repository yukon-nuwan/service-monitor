/**
 * 
 */
package com.yukon.service.monitor.converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yukon.service.monitor.domain.ServiceDomain;
import com.yukon.service.monitor.dto.ServiceDTO;

/**
 * @author Nuwan
 *
 */

@Component
public class ServiceConverter {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceConverter.class);
	
	
	/**
	 * Method to convert the service domain to DTO
	 * @param serviceDTO
	 * @param serviceDomain
	 * @return
	 */
	public ServiceDTO domainToDTO(ServiceDomain serviceDomain, ServiceDTO serviceDTO ) {
		
		if(serviceDomain != null) {
			
			serviceDTO.setId(serviceDomain.getId());
			serviceDTO.setHost(serviceDomain.getHost());
			serviceDTO.setPort(serviceDomain.getPort());
			
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            
            if(serviceDomain.getOutageStart() != null) {
            	serviceDTO.setOutageStart(LocalTime.parse(serviceDomain.getOutageStart().format(formatter)));
            }
            
            if(serviceDomain.getOutageEnd() != null) {
            	serviceDTO.setOutageEnd(LocalTime.parse(serviceDomain.getOutageEnd().format(formatter)));
            }
			
		}
		
		return serviceDTO;
		
	}
	

	/**
	 * Method to convert the service DTO to domain
	 * @param serviceDTO
	 * @param serviceDomain
	 * @return
	 */
	public ServiceDomain dtoToDomain(ServiceDTO serviceDTO, ServiceDomain serviceDomain) {
		
		if(serviceDTO != null) {
			
			serviceDomain.setId(serviceDTO.getId());
			serviceDomain.setHost(serviceDTO.getHost());
			serviceDomain.setPort(serviceDTO.getPort());
			serviceDomain.setOutageStart(serviceDTO.getOutageStart());
			serviceDomain.setOutageEnd(serviceDTO.getOutageEnd());
			
		}
		
		return serviceDomain;
		
	}



}
