/**
 * 
 */
package com.yukon.service.monitor.controller;

import java.util.List;

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
import com.yukon.service.monitor.dto.ResponseDTO;
import com.yukon.service.monitor.service.CallerService;
import com.yukon.service.monitor.service.CallerServiceService;
import com.yukon.service.monitor.util.MessageProperty;



/**
 * @author Nuwan
 *
 */

@RestController
@RequestMapping("/api/caller")
public class CallerRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(CallerRestController.class);

	@Autowired
	private MessageProperty messageProperty;
	
	@Autowired
	private CallerService callerService;
	
	@Autowired
	private CallerServiceService callerServiceService;
	

	
	/**
	 * Save caller end point
	 * @param callerDTO
	 * @return
	 */
	  @PostMapping(value={"/"})
	  public ResponseEntity<?> save(@RequestBody CallerDTO callerDTO) {
		  
		  ResponseDTO<CallerDTO> resultDTO = new ResponseDTO<CallerDTO>();
		  
		  callerDTO = callerService.save(callerDTO);
		  
		  resultDTO.setResult(callerDTO);
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
	  @PutMapping(value={"/{id}"})
	  public ResponseEntity<?> updateByCallerId(@PathVariable("id") Long id, @RequestBody CallerDTO callerDTO) {
		  
		  ResponseDTO<CallerDTO> resultDTO = new ResponseDTO<CallerDTO>();
		  
		  callerDTO.setId(id);
		  callerDTO = callerService.updateCallerByCallerId(callerDTO);
		  resultDTO.setResult(callerDTO);
		  resultDTO.setStatus(HttpStatus.OK.toString());
		  resultDTO.setDescription(messageProperty.getUpdate());
		  
	       return ResponseEntity.ok(resultDTO);
	   }
	  
	  
	  /**
	   * Delete by id end point
	   * @param id
	   * @param session
	   * @return
	   */
	  @DeleteMapping(value={"/{id}"})
	  public ResponseEntity<?> deleteByCallerId(@PathVariable("id") Long id) {
		  
		  ResponseDTO<CallerDTO> resultDTO = new ResponseDTO<CallerDTO>();
		  
		  callerService.deleteCallerByCallerId(id);
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
		  
		  CallerDTO callerDTO = callerService.getCallerByCallerId(id);
		  resultDTO.setResult(callerDTO);
		  resultDTO.setStatus(HttpStatus.OK.toString());
		  resultDTO.setDescription(messageProperty.getRecordFound());
		  
	       return ResponseEntity.ok(resultDTO);
	   }
	  
	  
	  /**
	   * Get by all callers end point
	   * @param callerDTO
	   * @param session
	   * @return
	   */
	  @GetMapping(value={"/all"})
	  public ResponseEntity<?> getAllCallers() {
		  
		  ResponseDTO<List<CallerDTO>> resultDTO = new ResponseDTO<List<CallerDTO>>();
		  
		  List<CallerDTO> callerDTOList = callerService.getAllCallers();
		  resultDTO.setResult(callerDTOList);
		  resultDTO.setStatus(HttpStatus.OK.toString());
		  resultDTO.setDescription(messageProperty.getRecordFound());
		  
	       return ResponseEntity.ok(resultDTO);
	   }
	  
	  
	  
	
	  
	
	  
	  /**
	   * Get by id end point
	   * @param callerDTO
	   * @param session
	   * @return
	   */
	  @GetMapping(value={"/{id}/services/status"})
	  public ResponseEntity<?> runCallerServices(@PathVariable("id") Long id) {
		  
		  ResponseDTO<CallerDTO> resultDTO = new ResponseDTO<CallerDTO>();
		  
		 callerService.runCallerServices(id);
		 // resultDTO.setResult(callerDTO);
		 // resultDTO.setStatus(HttpStatus.OK.toString());
		 // resultDTO.setDescription(messageProperty.getRecordFound());
		  
	       return ResponseEntity.ok(resultDTO);
	   }


}
