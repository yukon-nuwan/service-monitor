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

import com.yukon.service.monitor.dto.ServiceDTO;
import com.yukon.service.monitor.exception.RecordFoundException;
import com.yukon.service.monitor.exception.RecordNotFoundException;
import com.yukon.service.monitor.service.ServiceService;

/**
 * @author Nuwan
 *
 */


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TestServiceRestController {

	@Autowired
	private ServiceService serviceService;
	
	private static ServiceDTO serviceDTO;
	
	

	/**
	 * Testing service save
	 */
	@Test
	@Order(1)
	void serviceSave() {
		serviceDTO = new ServiceDTO();
		serviceDTO.setHost("www.google.com");
		serviceDTO.setPort(8080);
		
		 LocalTime outageStart = LocalTime.of(23,30);
		 LocalTime outageEnd = LocalTime.of(04,30);
		serviceDTO.setOutageStart(outageStart);
		serviceDTO.setOutageEnd(outageEnd);
		
		serviceDTO = serviceService.save(serviceDTO);
		assertNotNull(serviceDTO.getId());
	}
	
	
	/**
	 * Testing service record found exception while saving
	 */
	@Test
	@Order(2)
	void serviceSaveRecordFoundException() {
		RecordFoundException thrown = assertThrows(RecordFoundException.class, () -> serviceService.save(serviceDTO));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.CONFLICT.value()));
	}
	
	
	/**
	 * Testing service update
	 */
	@Test
	@Order(3)
	void serviceUpdate() {
		
		serviceDTO.setPort(8081);
	
		serviceDTO = serviceService.updateServiceByServiceId(serviceDTO);
		assertEquals(8081, serviceDTO.getPort());
	}
	
	
	/**
	 * Testing service record not found exception while updating
	 */
	@Test
	@Order(4)
	void serviceUpdateRecordNotFoundException() {
		ServiceDTO serviceDTOToUpdate = new ServiceDTO();
		serviceDTOToUpdate.setId(0l);
		
		RecordNotFoundException thrown = assertThrows(RecordNotFoundException.class, () -> serviceService.updateServiceByServiceId(serviceDTOToUpdate));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.NOT_FOUND.value()));
	}
	
	
	
	/**
	 * Testing service fetch by service id
	 */
	@Test
	@Order(5)
	void getServiceByServiceId() {
		serviceDTO = serviceService.getServiceByServiceId(serviceDTO.getId());
		assertNotNull(serviceDTO);
	}
	
	
	/**
	 * Testing service record not found exception while service fetch by service id
	 */
	@Test
	@Order(6)
	void getServiceByIdRecordNotFoundException() {
		RecordNotFoundException thrown = assertThrows(RecordNotFoundException.class, () -> serviceService.getServiceByServiceId(0l));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.NOT_FOUND.value()));
	}
	
	
	/**
	 * Testing service delete by service id
	 */
	@Test
	@Order(7)
	void serviceDeleteByServiceId() {
		assertTrue(serviceService.deleteServiceByServiceId(serviceDTO.getId()));
	}
	
	
	/**
	 * Testing service record not found exception while deleting by service id
	 */
	@Test
	@Order(8)
	void serviceDeleteByServiceIdRecordNotFoundException() {
		RecordNotFoundException thrown = assertThrows(RecordNotFoundException.class, () -> serviceService.deleteServiceByServiceId(0l));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.NOT_FOUND.value()));
	}
	

}
