/**
 * 
 */
package com.yukon.service.monitor.converter;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yukon.service.monitor.domain.CallerDomain;
import com.yukon.service.monitor.domain.CallerServiceDomain;
import com.yukon.service.monitor.dto.CallerDTO;
import com.yukon.service.monitor.dto.CallerServiceDTO;
import com.yukon.service.monitor.dto.ServiceDTO;

/**
 * @author Nuwan
 *
 */

@Component
public class CallerConverter {
	
	private static final Logger logger = LoggerFactory.getLogger(CallerConverter.class);
	
	@Autowired
	private ServiceConverter serviceConverter;
	
	@Autowired
	private CallerServiceConverter callerServiceConverter;
	
	

	
	/**
	 * Method to convert the caller domain to DTO
	 * @param callerDomain
	 * @param callerDTO
	 * @return
	 */
	public CallerDTO domainToDTO(CallerDomain callerDomain, CallerDTO callerDTO ) {
		
		if(callerDomain != null) {
			
			callerDTO.setId(callerDomain.getId());
			callerDTO.setName(callerDomain.getName());
			
			if(callerDomain.getCallerServiceDomainList() != null) {
				
				if(callerDTO.getCallerServiceDTOList() ==  null) {
					callerDTO.setCallerServiceDTOList(new ArrayList<CallerServiceDTO>());
				}
				
				callerDomain.getCallerServiceDomainList().stream().forEach( callerService -> {
					
					ServiceDTO serviceDTO = new ServiceDTO();
					serviceDTO = serviceConverter.domainToDTO(callerService.getService(), serviceDTO);
					
					CallerServiceDTO callerServiceDTO = new CallerServiceDTO();
					callerServiceDTO = callerServiceConverter.domainToDTO(callerService, callerServiceDTO);
					
					callerDTO.getCallerServiceDTOList().add(callerServiceDTO);
				});
			}
			
		}
		
		return callerDTO;
		
	}

	
	/**
	 * Method to convert the caller DTO to domain
	 * @param callerDTO
	 * @param callerDomain
	 * @return
	 */
	public CallerDomain dtoToDomain(CallerDTO callerDTO, CallerDomain callerDomain) {
		
		if(callerDTO != null) {
			
			callerDomain.setId(callerDTO.getId());
			callerDomain.setName(callerDTO.getName());
			
			if(callerDTO.getCallerServiceDTOList() != null) {
				
				if(callerDomain.getCallerServiceDomainList() ==  null) {
					callerDomain.setCallerServiceDomainList(new ArrayList<CallerServiceDomain>());
				}
				
				callerDTO.getCallerServiceDTOList().stream().forEach( callerServiceDTO -> {
					
					CallerServiceDomain callerServiceDomain = new CallerServiceDomain();
					callerServiceDomain = callerServiceConverter.dtoToDomain(callerServiceDTO, callerServiceDomain);
					
					callerDomain.getCallerServiceDomainList().add(callerServiceDomain);
				});
			}
			
		}
		
		return callerDomain;
		
	}



}
