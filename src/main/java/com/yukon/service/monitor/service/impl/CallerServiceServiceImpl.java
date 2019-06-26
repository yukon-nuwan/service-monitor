/**
 * 
 */
package com.yukon.service.monitor.service.impl;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.yukon.service.monitor.converter.CallerServiceConverter;
import com.yukon.service.monitor.domain.CallerServiceDomain;
import com.yukon.service.monitor.dto.CallerServiceDTO;
import com.yukon.service.monitor.dto.ServiceMessageDTO;
import com.yukon.service.monitor.exception.RecordFoundException;
import com.yukon.service.monitor.exception.RecordNotFoundException;
import com.yukon.service.monitor.repository.CallerServiceRepository;
import com.yukon.service.monitor.service.CallerServiceService;
import com.yukon.service.monitor.service.SchedulerService;
import com.yukon.service.monitor.util.MessageProperty;

/**
 * @author Nuwan
 *
 */

@Service
public class CallerServiceServiceImpl implements CallerServiceService{
	
	private static final Logger logger = LoggerFactory.getLogger(CallerServiceServiceImpl.class);
	
	@Autowired
	public MessageProperty messageProperty;
	
	@Autowired
	private CallerServiceRepository callerServiceRepository;
	
	@Autowired
	private CallerServiceConverter converter;
	
	@Autowired
	private SchedulerService schedulerService;
	
	
	
	/**
	 * Save callers' service
	 */
	@Override
	public CallerServiceDTO save(CallerServiceDTO callerServiceDTO) {
		CallerServiceDomain callerServiceDB = callerServiceRepository.findByCallerAndService(callerServiceDTO.getCallerId(), callerServiceDTO.getServiceId()).orElse(new CallerServiceDomain());
		checkCallerExist(callerServiceDB);
		callerServiceDTO = processCallerSaveOrUpdate(callerServiceDTO,callerServiceDB);
		schedulerService.setupFutureTask(callerServiceDTO);
		return callerServiceDTO;
	}
	
	
	/**
	 * Delete caller service by caller
	 */
	@Override
	public Boolean deleteCallerService(Long callerId, Long serviceId) {
		
		CallerServiceDomain callerServiceDomain = callerServiceRepository.findByCallerAndService(callerId, serviceId).orElse(new CallerServiceDomain());

		checkCallerServiceNotFound(callerServiceDomain);
		callerServiceRepository.deleteCallerServiceById(callerServiceDomain.getId());
		
		schedulerService.stop(callerId+"_"+serviceId);
		
		return true;
	}

	
	/**
	 * Method to check is caller not found
	 */
	private void checkCallerServiceNotFound(CallerServiceDomain callerServiceDB ) {
		
		if(callerServiceDB == null || callerServiceDB.getId() == null) {
			throw new RecordNotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), messageProperty.getRecordNotFound());
		}
	}
	
	/**
	 * Method to convert data and save or update caller
	 */
	private CallerServiceDTO processCallerSaveOrUpdate(CallerServiceDTO callerDTO, CallerServiceDomain callerServiceDB) {
		callerServiceDB = converter.dtoToDomain(callerDTO, callerServiceDB);
		
		callerServiceDB = callerServiceRepository.save(callerServiceDB);
		callerServiceDB = callerServiceRepository.findById(callerServiceDB.getId()).orElse(new CallerServiceDomain());
		return converter.domainToDTO(callerServiceDB, callerDTO);
	}
	
	
	
	/**
	 * Method to check is caller exist
	 */
	private void checkCallerExist(CallerServiceDomain callerServiceDB ) {
		
		if(callerServiceDB != null && callerServiceDB.getId() != null) {
			throw new RecordFoundException(String.valueOf(HttpStatus.CONFLICT.value()), messageProperty.getRecordExist());
		}
	}


	/**
	 * Find all callers' service
	 */
	@Override
	public List<CallerServiceDTO> findAlCallerService() {
		
		List<CallerServiceDomain> callerServiceDomainList =  callerServiceRepository.findAll();
		
		
		List<CallerServiceDTO> callerServiceDTOList = new ArrayList<CallerServiceDTO>();
		
		if(callerServiceDomainList != null) {
			callerServiceDomainList.stream().forEach( callerServiceDB -> {
				callerServiceDTOList.add(converter.domainToDTO(callerServiceDB, new CallerServiceDTO()));
			});
		}
		return callerServiceDTOList;
	}


	/**
	 * call for the service status and send to the subscriber
	 */
	@Override
	public ServiceMessageDTO runCallerServices(CallerServiceDTO callerServiceDTO) {
		
		Boolean isAlive = checkServiceStatus(callerServiceDTO.getServiceDTO().getHost(), callerServiceDTO.getServiceDTO().getPort());
		ServiceMessageDTO serviceMessageDTO = setServiceMessageData(callerServiceDTO, isAlive);
		
		
		if(isAlive) {
			serviceMessageDTO.setMessage(callerServiceDTO.getServiceDTO().getHost()+" Working Fine");
			logger.info(callerServiceDTO.getServiceDTO().getHost()+" Working Fine");
		}else {
			serviceMessageDTO.setMessage(callerServiceDTO.getServiceDTO().getHost()+" Service Down");
			logger.info(callerServiceDTO.getServiceDTO().getHost()+" Service Down");
		}
		
		return serviceMessageDTO;
		
		
		
	}

	
	/**
	 * Set service message data
	 * @param callerServiceDTO
	 * @param isAlive
	 * @return
	 */
	private ServiceMessageDTO setServiceMessageData(CallerServiceDTO callerServiceDTO, Boolean isAlive) {
		ServiceMessageDTO serviceMessageDTO = new ServiceMessageDTO();
		serviceMessageDTO.setIsAlive(isAlive);
		serviceMessageDTO.setCallerId(callerServiceDTO.getCallerId());
		serviceMessageDTO.setServiceId(callerServiceDTO.getServiceId());
		serviceMessageDTO.setHost(callerServiceDTO.getServiceDTO().getHost());
		serviceMessageDTO.setPort(callerServiceDTO.getServiceDTO().getPort());
		return serviceMessageDTO;
	}
	
	
	/**
	 * check the service status
	 */
	private Boolean checkServiceStatus(String host, Integer port){
	    Socket socket = null;
	    try{
	    	socket = new Socket(host, port);
	        return true;
	    }catch (Exception e){
	        return false;
	    }
	    
	    finally{
	        if(socket != null) {
	            try {
	            	socket.close();
	            }catch(Exception e){
	            	logger.info(e.getMessage());
	            }
	        }
	    }
	}


	/**
	 * Update caller service
	 */
	@Override
	public CallerServiceDTO update(CallerServiceDTO callerServiceDTO) {
		CallerServiceDomain callerServiceDB = callerServiceRepository.findByCallerAndService(callerServiceDTO.getCallerId(), callerServiceDTO.getServiceId()).orElse(new CallerServiceDomain());
		checkCallerServiceNotFound(callerServiceDB);
		callerServiceDTO = processCallerSaveOrUpdate(callerServiceDTO,callerServiceDB);
		
		schedulerService.stop(callerServiceDTO.getCallerId()+"_"+callerServiceDTO.getServiceId());
		schedulerService.setupFutureTask(callerServiceDTO);
		
		return callerServiceDTO;
	}





	





	
	

}
