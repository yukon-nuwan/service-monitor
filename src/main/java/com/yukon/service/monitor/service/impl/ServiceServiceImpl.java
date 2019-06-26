/**
 * 
 */
package com.yukon.service.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.yukon.service.monitor.converter.ServiceConverter;
import com.yukon.service.monitor.domain.ServiceDomain;
import com.yukon.service.monitor.dto.ServiceDTO;
import com.yukon.service.monitor.exception.RecordFoundException;
import com.yukon.service.monitor.exception.RecordNotFoundException;
import com.yukon.service.monitor.repository.ServiceRepository;
import com.yukon.service.monitor.service.ServiceService;
import com.yukon.service.monitor.util.MessageProperty;
/**
 * @author Nuwan
 *
 */

@Service
public class ServiceServiceImpl implements ServiceService{
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);
	
	
	@Autowired
	public MessageProperty messageProperty;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceConverter converter;
	
	
	
	/**
	 * Save service
	 */
	@Override
	public ServiceDTO save(ServiceDTO serviceDTO) {
		
		ServiceDomain serviceDB = serviceRepository.findByHostAndPort(serviceDTO.getHost(), serviceDTO.getPort()).orElse(new ServiceDomain());
		checkServiceExist(serviceDB);
		serviceDTO = processServiceSaveOrUpdate(serviceDTO,serviceDB);
		
		return serviceDTO;
	}
	
	/**
	 * Method to update the service 
	 */
	@Override
	public ServiceDTO updateServiceByServiceId(ServiceDTO serviceDTO) {
		
		ServiceDomain serviceDB = serviceRepository.findById(serviceDTO.getId()).orElse(new ServiceDomain());	
		checkServiceNotFound(serviceDB );
		serviceDTO = processServiceSaveOrUpdate(serviceDTO,serviceDB);
		
		return serviceDTO;
		
	}
	
	/**
	 * Delete service by id
	 */
	@Override
	public Boolean deleteServiceByServiceId(Long  id) {
		ServiceDomain serviceDB = serviceRepository.findById(id).orElse(null);	
		checkServiceNotFound(serviceDB);
		serviceRepository.deleteById(serviceDB.getId());
		
		return true;
	}
	
	
	/**
	 * Get service by service id
	 */
	@Override
	public ServiceDTO getServiceByServiceId(Long  id) {
		ServiceDomain serviceDB = serviceRepository.findById(id).orElse(null);	
		checkServiceNotFound(serviceDB);
		
		return converter.domainToDTO(serviceDB, new ServiceDTO());
		
	}
	
	/**
	 * Get All service 
	 */
	@Override
	public List<ServiceDTO> getAllService() {
		
		List<ServiceDomain> serviceDBList = serviceRepository.findAll();	
		
		List<ServiceDTO> serviceDTOList = new ArrayList<ServiceDTO>();
		if(serviceDBList != null && !serviceDBList.isEmpty()) {
			serviceDBList.stream().forEach(serviceDB -> {
				serviceDTOList.add(converter.domainToDTO(serviceDB, new ServiceDTO()));
			});
			
		}
		return serviceDTOList;
		
	}
	
	
	
	/**
	 * Method to convert data and save or update service
	 */
	private ServiceDTO processServiceSaveOrUpdate(ServiceDTO serviceDTO, ServiceDomain serviceDB) {
		serviceDB = converter.dtoToDomain(serviceDTO, serviceDB);
		
		serviceDB = serviceRepository.save(serviceDB);
		return converter.domainToDTO(serviceDB, serviceDTO);
	}
	
	
	/**
	 * Method to check is service not found
	 */
	private void checkServiceNotFound(ServiceDomain serviceDB ) {
		
		if(serviceDB == null || serviceDB.getId() == null) {
			throw new RecordNotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), messageProperty.getRecordNotFound());
		}
	}
	
	
	/**
	 * Method to check is service exist
	 */
	private void checkServiceExist(ServiceDomain serviceDB ) {
		
		if(serviceDB != null && serviceDB.getId() != null) {
			throw new RecordFoundException(String.valueOf(HttpStatus.CONFLICT.value()), messageProperty.getRecordExist());
		}
	}
	
	
	

}
