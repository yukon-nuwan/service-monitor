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
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.yukon.service.monitor.converter.CallerConverter;
import com.yukon.service.monitor.domain.CallerDomain;
import com.yukon.service.monitor.dto.CallerDTO;
import com.yukon.service.monitor.dto.ServiceMessageDTO;
import com.yukon.service.monitor.exception.RecordFoundException;
import com.yukon.service.monitor.exception.RecordNotFoundException;
import com.yukon.service.monitor.repository.CallerRepository;
import com.yukon.service.monitor.service.CallerService;
import com.yukon.service.monitor.util.MessageProperty;

/**
 * @author Nuwan
 *
 */

@Service
public class CallerServiceImpl implements CallerService{
	
	private static final Logger logger = LoggerFactory.getLogger(CallerServiceImpl.class);
	
	@Autowired
	public MessageProperty messageProperty;
	
	@Autowired
	private CallerRepository callerRepository;
	
	@Autowired
	private CallerConverter converter;
	

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	/**
	 * Save caller
	 */
	@Override
	public CallerDTO save(CallerDTO callerDTO) {
		
		CallerDomain callerDB = callerRepository.findCallerByName(callerDTO.getName()).orElse(new CallerDomain());
		checkCallerExist(callerDB);
		callerDTO = processCallerSaveOrUpdate(callerDTO,callerDB);
		
		return callerDTO;
	}
	
	/**
	 * Method to update the caller 
	 */
	@Override
	public CallerDTO updateCallerByCallerId(CallerDTO callerDTO) {
		
		CallerDomain callerDB = callerRepository.findById(callerDTO.getId()).orElse(new CallerDomain());	
		checkNotFound(callerDB );
		callerDTO = processCallerSaveOrUpdate(callerDTO,callerDB);
		
		return callerDTO;
		
	}
	
	/**
	 * Delete caller by id
	 */
	@Override
	public Boolean deleteCallerByCallerId(Long  id) {
		CallerDomain callerDB = callerRepository.findById(id).orElse(null);	
		checkNotFound(callerDB);
		callerRepository.deleteById(callerDB.getId());
		
		return true;
	}
	
	
	/**
	 * Get caller by caller id
	 */
	@Override
	public CallerDTO getCallerByCallerId(Long  id) {
		CallerDomain callerDB = callerRepository.findById(id).orElse(null);	
		checkNotFound(callerDB);
		
		return converter.domainToDTO(callerDB, new CallerDTO());
		
	}
	
	/**
	 * Get caller by caller id
	 */
	@Override
	public List<CallerDTO> getAllCallers() {
		List<CallerDomain> callerDBList = callerRepository.findAll();	
		
		List<CallerDTO> callerDTOList = new ArrayList<CallerDTO>();
		if(callerDBList != null && !callerDBList.isEmpty()) {
			callerDBList.stream().forEach( caller ->{
				CallerDTO callerDTO = converter.domainToDTO(caller, new CallerDTO());
				callerDTOList.add(callerDTO);
			});
		}
		
		
		return callerDTOList;
		
	}
	
	
	
	
	/**
	 * Run caller assign services to check the status
	 */
	@Override
	public void runCallerServices(Long id) {
		
		CallerDomain callerDB = callerRepository.findById(id).orElse(new CallerDomain());
		
        if(callerDB.getCallerServiceDomainList() != null) {
        	callerDB.getCallerServiceDomainList().stream().forEach( callerService -> {
        		ServiceMessageDTO serviceMessageDTO = new ServiceMessageDTO();
        		serviceMessageDTO.setIsAlive(true);
        		serviceMessageDTO.setMessage("runCallerServices Working");
        		this.messagingTemplate.convertAndSend("/topic/greetings", serviceMessageDTO);
        	});
        }
		
		
		
	}
	
	
	/**
	 * Method to convert data and save or update caller
	 */
	private CallerDTO processCallerSaveOrUpdate(CallerDTO callerDTO, CallerDomain callerDB) {
		callerDB = converter.dtoToDomain(callerDTO, callerDB);
		
		callerDB = callerRepository.save(callerDB);
		return converter.domainToDTO(callerDB, callerDTO);
	}
	
	
	/**
	 * Method to check is caller not found
	 */
	private void checkNotFound(CallerDomain callerDB ) {
		
		if(callerDB == null || callerDB.getId() == null) {
			throw new RecordNotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), messageProperty.getRecordNotFound());
		}
	}
	
	
	/**
	 * Method to check is caller exist
	 */
	private void checkCallerExist(CallerDomain callerDB ) {
		
		if(callerDB != null && callerDB.getId() != null) {
			throw new RecordFoundException(String.valueOf(HttpStatus.CONFLICT.value()), messageProperty.getRecordExist());
		}
	}


}
