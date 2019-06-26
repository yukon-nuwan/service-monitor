/**
 * 
 */
package com.yukon.service.monitor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.yukon.service.monitor.dto.CallerDTO;
import com.yukon.service.monitor.exception.RecordFoundException;
import com.yukon.service.monitor.exception.RecordNotFoundException;
import com.yukon.service.monitor.service.CallerService;


/**
 * @author Nuwan
 *
 */


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TestCallerRestController {
	
	@Autowired
	private CallerService callerService;
	
	private static CallerDTO callerDTO;
	
	

	/**
	 * Testing caller save
	 */
	@Test
	@Order(1)
	void callerSave() {
		callerDTO = new CallerDTO();
		callerDTO.setName("Test Caller Domain");
		
		callerDTO = callerService.save(callerDTO);
		assertNotNull(callerDTO.getId());
	}
	
	
	/**
	 * Testing caller record found exception while saving
	 */
	@Test
	@Order(2)
	void callerSaveRecordFoundException() {
		RecordFoundException thrown = assertThrows(RecordFoundException.class, () -> callerService.save(callerDTO));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.CONFLICT.value()));
	}
	
	
	/**
	 * Testing caller update
	 */
	@Test
	@Order(3)
	void callerUpdate() {
		
		callerDTO.setName("Test Caller Updated");
	
		callerDTO = callerService.updateCallerByCallerId(callerDTO);
		assertEquals("Test Caller Updated", callerDTO.getName());
	}
	
	
	/**
	 * Testing caller record not found exception while updating
	 */
	@Test
	@Order(4)
	void callerUpdateRecordNotFoundException() {
		CallerDTO callerDTOToUpdate = new CallerDTO();
		callerDTOToUpdate.setName("Test Caller");
		callerDTOToUpdate.setId(0l);
		
		RecordNotFoundException thrown = assertThrows(RecordNotFoundException.class, () -> callerService.updateCallerByCallerId(callerDTOToUpdate));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.NOT_FOUND.value()));
	}
	
	
	
	/**
	 * Testing caller fetch by caller id
	 */
	@Test
	@Order(5)
	void getCallerByCallerId() {
		callerDTO = callerService.getCallerByCallerId(callerDTO.getId());
		assertNotNull(callerDTO);
	}
	
	
	/**
	 * Testing caller record not found exception while caller fetch by caller id
	 */
	@Test
	@Order(6)
	void getCallerByIdRecordNotFoundException() {
		RecordNotFoundException thrown = assertThrows(RecordNotFoundException.class, () -> callerService.getCallerByCallerId(0l));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.NOT_FOUND.value()));
	}
	

	
	
	/**
	 * Testing caller delete by caller id
	 */
	@Test
	@Order(7)
	void callerDeleteByCallerId() {
		assertTrue(callerService.deleteCallerByCallerId(callerDTO.getId()));
	}
	
	
	/**
	 * Testing caller record not found exception while deleting by caller id
	 */
	@Test
	@Order(8)
	void callerDeleteByCallerIdRecordNotFoundException() {
		RecordNotFoundException thrown = assertThrows(RecordNotFoundException.class, () -> callerService.deleteCallerByCallerId(0l));
		assertEquals(thrown.getErrorCode(),String.valueOf(HttpStatus.NOT_FOUND.value()));
	}
	
	
	
	

}
