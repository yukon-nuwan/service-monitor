/**
 * 
 */
package com.yukon.service.monitor.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yukon.service.monitor.dto.ResponseDTO;
import com.yukon.service.monitor.exception.RecordNotFoundException;

/**
 * @author Nuwan
 *
 */

@ControllerAdvice
public class ServiceMonitorExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	/**
	 * User record not found exception handler 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<ResponseDTO<Object>> handleUserNotFoundException(RecordNotFoundException ex) {
	    
	    ResponseDTO<Object> errorResponse = new ResponseDTO<Object>();
	    errorResponse.setStatus(ex.getErrorCode());
	    errorResponse.setDescription(ex.getErrorDescription());
	    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}