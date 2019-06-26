/**
 * 
 */
package com.yukon.service.monitor.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yukon.service.monitor.domain.CallerDomain;
import com.yukon.service.monitor.domain.CallerServiceDomain;
import com.yukon.service.monitor.domain.ServiceDomain;
import com.yukon.service.monitor.dto.CallerServiceDTO;
import com.yukon.service.monitor.dto.ServiceDTO;

/**
 * @author Nuwan
 *
 */

@Component
public class CallerServiceConverter {
	
	private static final Logger logger = LoggerFactory.getLogger(CallerServiceConverter.class);
	
	
	/**
	 * Method to convert the caller service domain to DTO
	 * @param callerServiceDomain
	 * @param callerServiceDTO
	 * @return
	 */
	public CallerServiceDTO domainToDTO(CallerServiceDomain callerServiceDomain, CallerServiceDTO callerServiceDTO ) {
		
		if(callerServiceDomain != null) {
			callerServiceDTO.setId(callerServiceDomain.getId());
			callerServiceDTO.setCallerId(callerServiceDomain.getCaller().getId());
			callerServiceDTO.setServiceId(callerServiceDomain.getService().getId());
			callerServiceDTO.setGraceTime(callerServiceDomain.getGraceTime());
			callerServiceDTO.setPollingFrequency(callerServiceDomain.getPollingFrequency());	
			
			ServiceDTO serviceDTO = new ServiceDTO();
			serviceDTO.setId(callerServiceDomain.getService().getId());
			serviceDTO.setHost(callerServiceDomain.getService().getHost());
			serviceDTO.setPort(callerServiceDomain.getService().getPort());
			serviceDTO.setOutageStart(callerServiceDomain.getService().getOutageStart());
			serviceDTO.setOutageEnd(callerServiceDomain.getService().getOutageEnd());
			callerServiceDTO.setServiceDTO(serviceDTO);
			
		}
		
		return callerServiceDTO;
		
	}
	
	
	/**
	 * Method to convert the caller service DTO to domain
	 * @param callerServiceDTO
	 * @param callerServiceDomain
	 * @return
	 */
	public CallerServiceDomain dtoToDomain(CallerServiceDTO callerServiceDTO, CallerServiceDomain callerServiceDomain) {
		
		if(callerServiceDTO != null) {
			
			callerServiceDomain.setId(callerServiceDTO.getId());
			
			CallerDomain callerDomain = new CallerDomain();
			callerDomain.setId(callerServiceDTO.getCallerId());
			callerServiceDomain.setCaller(callerDomain);
			
			ServiceDomain serviceDomain = new ServiceDomain();
			serviceDomain.setId(callerServiceDTO.getServiceId());
			
			if(callerServiceDTO.getServiceDTO() != null && callerServiceDTO.getServiceDTO().getId() != null) {
				
				serviceDomain.setHost(callerServiceDTO.getServiceDTO().getHost());
				serviceDomain.setPort(callerServiceDTO.getServiceDTO().getPort());
				serviceDomain.setOutageStart(callerServiceDTO.getServiceDTO().getOutageStart());
				serviceDomain.setOutageEnd(callerServiceDTO.getServiceDTO().getOutageEnd());
				
			}
			callerServiceDomain.setService(serviceDomain);
			callerServiceDomain.setGraceTime(callerServiceDTO.getGraceTime());
			callerServiceDomain.setPollingFrequency(callerServiceDTO.getPollingFrequency());	
			
		}
		
		return callerServiceDomain;
		
	}



}
