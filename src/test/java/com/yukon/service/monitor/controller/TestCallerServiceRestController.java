/**
 * 
 */
package com.yukon.service.monitor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.yukon.service.monitor.dto.CallerDTO;
import com.yukon.service.monitor.dto.CallerServiceDTO;
import com.yukon.service.monitor.dto.ServiceDTO;
import com.yukon.service.monitor.dto.ServiceMessageDTO;
import com.yukon.service.monitor.exception.RecordFoundException;
import com.yukon.service.monitor.exception.RecordNotFoundException;
import com.yukon.service.monitor.service.CallerService;
import com.yukon.service.monitor.service.CallerServiceService;
import com.yukon.service.monitor.service.ServiceService;


/**
 * @author Nuwan
 *
 */


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TestCallerServiceRestController {
	
	@Autowired
	private CallerService callerService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private CallerServiceService callerServiceService;
	
	private static CallerDTO callerDTO;
	
	private static ServiceDTO serviceDTO;
	
	private static CallerServiceDTO callerServiceDTO;
	
	
	/**
	 * Testing service save
	 */
	@Test
	@Order(1)
	void serviceSave() {
		serviceDTO = new ServiceDTO();
		serviceDTO.setHost("localhost");
		serviceDTO.setPort(8080);
		
		 LocalTime outageStart = LocalTime.of(23,30);
		 LocalTime outageEnd = LocalTime.of(04,30);
		serviceDTO.setOutageStart(outageStart);
		serviceDTO.setOutageEnd(outageEnd);
		
		serviceDTO = serviceService.save(serviceDTO);
		assertNotNull(serviceDTO.getId());
	}
	
	/**
	 * Testing caller save
	 */
	@Test
	@Order(2)
	void callerSave() {
		callerDTO = new CallerDTO();
		callerDTO.setName("Test Caller Domain");
		
		callerDTO = callerService.save(callerDTO);
		assertNotNull(callerDTO.getId());
	}
	
	
	
	/**
	 * Testing caller service save
	 */
	@Test
	@Order(3)
	void seveCallerService() {
		callerServiceDTO = new CallerServiceDTO();
		callerServiceDTO.setCallerId(callerDTO.getId());
		callerServiceDTO.setGraceTime(10);
		callerServiceDTO.setPollingFrequency(5);
		callerServiceDTO.setServiceId(serviceDTO.getId());
		
		
		callerServiceDTO = callerServiceService.save(callerServiceDTO);
		assertNotNull(callerServiceDTO);
		assertNotNull(callerServiceDTO.getId());

	}

	
	
	/**
	 * Testing caller service record found exception while saving
	 */
	@Test
	@Order(4)
	void callerServiceSaveRecordFoundException() {
		RecordFoundException thrown = assertThrows(RecordFoundException.class, () -> callerServiceService.save(callerServiceDTO));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.CONFLICT.value()));
	}
	
	
	/**
	 * Testing caller service update
	 */
	@Test
	@Order(5)
	void callerServiceUpdate() {
		

		CallerServiceDTO callerServiceDTOToUpdate = new CallerServiceDTO();
		callerServiceDTOToUpdate.setId(callerServiceDTO.getId());
		callerServiceDTOToUpdate.setCallerId(callerServiceDTO.getCallerId());
		callerServiceDTOToUpdate.setGraceTime(callerServiceDTO.getGraceTime());
		callerServiceDTOToUpdate.setPollingFrequency(2);
		callerServiceDTOToUpdate.setServiceId(callerServiceDTO.getServiceId());
		callerServiceDTO = callerServiceService.update(callerServiceDTOToUpdate);
		assertEquals(2, callerServiceDTOToUpdate.getPollingFrequency());
	}
	
	
	/**
	 * Testing caller service record not found exception while updating
	 */
	@Test
	@Order(6)
	void callerServiceUpdateRecordNotFoundException() {
		CallerServiceDTO callerServiceDTOToUpdate = new CallerServiceDTO();
		callerServiceDTOToUpdate.setId(0l);
		callerServiceDTOToUpdate.setCallerId(0l);
		callerServiceDTOToUpdate.setServiceId(0l);
		
		RecordNotFoundException thrown = assertThrows(RecordNotFoundException.class, () -> callerServiceService.update(callerServiceDTOToUpdate));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.NOT_FOUND.value()));
	}
	
	

	
	/**
	 * Testing caller service delete by caller and service
	 */
	@Test
	@Order(7)
	void callerServiceDeleteByCallerAndService() {
		assertTrue(callerServiceService.deleteCallerService(callerServiceDTO.getCallerId(), callerServiceDTO.getServiceId()));
	}
	
	
	/**
	 * Testing caller service record not found exception while deleting by caller and service
	 */
	@Test
	@Order(8)
	void callerServiceDeleteByCallerAndServiceRecordNotFoundException() {
		RecordNotFoundException thrown = assertThrows(RecordNotFoundException.class, () -> callerServiceService.deleteCallerService(callerServiceDTO.getCallerId(), callerServiceDTO.getServiceId()));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.NOT_FOUND.value()));
	}
	
	/**
	 * Get Service message DTO 
	 * @return 
	 */
	@Test
	@Order(9)
	void runCallerServices() {
		
		callerServiceDTO = new CallerServiceDTO();
		callerServiceDTO.setCallerId(0l);
		callerServiceDTO.setServiceId(0l);
		callerServiceDTO.setPollingFrequency(2);
		callerServiceDTO.setGraceTime(5);
		
		ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setHost("localhost");
		serviceDTO.setPort(8080);
		callerServiceDTO.setServiceDTO(serviceDTO);
		ServiceMessageDTO serviceMessageDTO = callerServiceService.runCallerServices(callerServiceDTO);
		
		assertEquals(true, serviceMessageDTO.getIsAlive());

	}
	
	
	/**
	 * caller delete by caller id
	 */
	@Test
	@Order(10)
	void callerDeleteByCallerId() {
		callerService.deleteCallerByCallerId(callerDTO.getId());
	}
	
	/**
	 * delete service by service id
	 */
	@Test
	@Order(11)
	void serviceDeleteByCallerId() {
		serviceService.deleteServiceByServiceId(serviceDTO.getId());
	}
	
	
	
	

}
