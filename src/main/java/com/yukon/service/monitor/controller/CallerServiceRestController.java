/**
 * 
 */
package com.yukon.service.monitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukon.service.monitor.dto.CallerDTO;
import com.yukon.service.monitor.dto.CallerServiceDTO;
import com.yukon.service.monitor.dto.ResponseDTO;
import com.yukon.service.monitor.service.CallerServiceService;
import com.yukon.service.monitor.util.MessageProperty;



/**
 * @author Nuwan
 *
 */

@RestController
@RequestMapping("/api/caller/{id}/service")
public class CallerServiceRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(CallerServiceRestController.class);

	@Autowired
	private MessageProperty messageProperty;
	
	@Autowired
	private CallerServiceService callerServiceService;
	
	
	

	
	/**
	 * Save caller end point
	 * @param callerDTO
	 * @return
	 */
	  @PostMapping(value={"/"})
	  public ResponseEntity<?> save(@RequestBody CallerServiceDTO callerServiceDTO) {
		  
		  ResponseDTO<CallerServiceDTO> resultDTO = new ResponseDTO<CallerServiceDTO>();
		  
		  callerServiceDTO = callerServiceService.save(callerServiceDTO);
		  
		  resultDTO.setResult(callerServiceDTO);
		  resultDTO.setStatus(HttpStatus.CREATED.toString());
		  resultDTO.setDescription(messageProperty.getSave());
		  
	      return ResponseEntity.ok(resultDTO);
	   }

	  
	  /**
	   * Update caller by id end point
	   * @param callerDTO
	   * @param session
	   * @return
	   */
	  @PutMapping(value={"/{serviceId}"})
	  public ResponseEntity<?> updateCallerService(@RequestBody CallerServiceDTO callerServiceDTO) {
		  
		  ResponseDTO<CallerServiceDTO> resultDTO = new ResponseDTO<CallerServiceDTO>();
		  
		  callerServiceDTO = callerServiceService.update(callerServiceDTO);
		  
		  resultDTO.setResult(callerServiceDTO);
		  resultDTO.setStatus(HttpStatus.CREATED.toString());
		  resultDTO.setDescription(messageProperty.getSave());
		  
	       return ResponseEntity.ok(resultDTO);
	   }
	  
	  
	  /**
	   * Delete by id end point
	   * @param id
	   * @param session
	   * @return
	   */
	  @DeleteMapping(value={"/{serviceId}"})
	  public ResponseEntity<?> deleteCallerService(@PathVariable("id") Long callerId,@PathVariable("serviceId") Long serviceId) {
		  
		  ResponseDTO<CallerDTO> resultDTO = new ResponseDTO<CallerDTO>();
		  
		  callerServiceService.deleteCallerService(callerId, serviceId);
		  resultDTO.setStatus(HttpStatus.OK.toString());
		  resultDTO.setDescription(messageProperty.getDelete());
		  
	      return ResponseEntity.ok(resultDTO);
	   }
	  
	  
	  /**
	   * Get by id end point
	   * @param callerDTO
	   * @param session
	   * @return
	   */
	  @GetMapping(value={"/{id}"})
	  public ResponseEntity<?> getByCallerId(@PathVariable("id") Long id) {
		  
		  ResponseDTO<CallerDTO> resultDTO = new ResponseDTO<CallerDTO>();
		  
		  //CallerDTO callerDTO = callerService.getCallerByCallerId(id);
		  //resultDTO.setResult(callerDTO);
		  resultDTO.setStatus(HttpStatus.OK.toString());
		  resultDTO.setDescription(messageProperty.getRecordFound());
		  
	       return ResponseEntity.ok(resultDTO);
	   }
	  
	  
	  

}
