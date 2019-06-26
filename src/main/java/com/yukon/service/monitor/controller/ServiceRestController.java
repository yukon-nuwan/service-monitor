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

import com.yukon.service.monitor.dto.ResponseDTO;
import com.yukon.service.monitor.dto.ServiceDTO;
import com.yukon.service.monitor.service.ServiceService;
import com.yukon.service.monitor.util.MessageProperty;



/**
 * @author Nuwan
 *
 */

@RestController
@RequestMapping("/api/service")
public class ServiceRestController {
	
private static final Logger logger = LoggerFactory.getLogger(ServiceRestController.class);

	@Autowired
	private MessageProperty messageProperty;
	
	@Autowired
	private ServiceService serviceService;
	
	/**
	 * Save service end point
	 * @param serviceDTO
	 * @return
	 */
	  @PostMapping(value={"/"})
	  public ResponseEntity<?> save(@RequestBody ServiceDTO serviceDTO) {
		  
		  ResponseDTO<ServiceDTO> resultDTO = new ResponseDTO<ServiceDTO>();
		  
		  serviceDTO = serviceService.save(serviceDTO);
		  
		  resultDTO.setResult(serviceDTO);
		  resultDTO.setStatus(HttpStatus.CREATED.toString());
		  resultDTO.setDescription(messageProperty.getSave());
		  
	      return ResponseEntity.ok(resultDTO);
	   }
	  
	  
	  /**
	   * Update service by id end point
	   * @param serviceDTO
	   * @param session
	   * @return
	   */
	  @PutMapping(value={"/{id}"})
	  public ResponseEntity<?> updateByServiceId(@PathVariable("id") Long id, @RequestBody ServiceDTO serviceDTO) {
		  
		  ResponseDTO<ServiceDTO> resultDTO = new ResponseDTO<ServiceDTO>();
		  
		  serviceDTO.setId(id);
		  serviceDTO = serviceService.updateServiceByServiceId(serviceDTO);
		  resultDTO.setResult(serviceDTO);
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
	  public ResponseEntity<?> deleteByServiceId(@PathVariable("id") Long id) {
		  
		  ResponseDTO<ServiceDTO> resultDTO = new ResponseDTO<ServiceDTO>();
		  
		  serviceService.deleteServiceByServiceId(id);
		  resultDTO.setStatus(HttpStatus.OK.toString());
		  resultDTO.setDescription(messageProperty.getDelete());
		  
	      return ResponseEntity.ok(resultDTO);
	   }
	  
	  
	  /**
	   * Get by id end point
	   * @param serviceDTO
	   * @param session
	   * @return
	   */
	  @GetMapping(value={"/{id}"})
	  public ResponseEntity<?> getByServiceId(@PathVariable("id") Long id) {
		  
		  ResponseDTO<ServiceDTO> resultDTO = new ResponseDTO<ServiceDTO>();
		  
		  ServiceDTO serviceDTO = serviceService.getServiceByServiceId(id);
		  resultDTO.setResult(serviceDTO);
		  resultDTO.setStatus(HttpStatus.OK.toString());
		  resultDTO.setDescription(messageProperty.getRecordFound());
		  
	       return ResponseEntity.ok(resultDTO);
	   }
	  
	  /**
	   * Get by id end point
	   * @param serviceDTO
	   * @param session
	   * @return
	   */
	  @GetMapping(value={"/all"})
	  public ResponseEntity<?> getAllService() {
		  
		  ResponseDTO<List<ServiceDTO>> resultDTO = new ResponseDTO<List<ServiceDTO>>();
		  
		  List<ServiceDTO> serviceDTOList = serviceService.getAllService();
		  resultDTO.setResult(serviceDTOList);
		  resultDTO.setStatus(HttpStatus.OK.toString());
		  resultDTO.setDescription(messageProperty.getRecordFound());
		  
	       return ResponseEntity.ok(resultDTO);
	   }


	  
	  

}
