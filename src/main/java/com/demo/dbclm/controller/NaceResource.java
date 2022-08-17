package com.demo.dbclm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dbclm.domain.NaceData;
import com.demo.dbclm.service.NaceService;


/**
 * This is the resource class representing NACH (Nomenclature of Economic Activities) data.
 * 
 * @author 7338877
 *
 */
@RestController
public class NaceResource {  

	@Autowired
	private NaceService naceService;
	
	private static final Logger logger = LoggerFactory.getLogger(NaceResource.class);
	
	/**
	 * Controller method to get NACH data based on provided order id.
	 * 
	 * @param orderId
	 * @return
	 */
	
	@GetMapping("/getNaceDataByorderId/{orderId}")
	public ResponseEntity<NaceData> getNaceDataByorderId(@PathVariable Long orderId) {
		logger.info("Entering getNaceDataByorderId() with orderId: "+ orderId);
		NaceData naceDTO = naceService.getNaceDataByorderId(orderId);
		logger.info("Exiting getNaceDataByorderId() with naceDTO: "+ naceDTO);
        return new ResponseEntity<>(naceDTO, HttpStatus.OK);
	}
	
	/**
	 * Controller method to insert NACH data.
	 * 
	 * @param naceDTOBody Json to provide as body to post request.
	 * @return
	 */
	
	@PostMapping("/postNaceData")
	public ResponseEntity<String> postNaceData(@RequestBody NaceData naceDTOBody) {
		logger.info("Entering postNaceData() with @RequestBody: "+ naceDTOBody);
		String response = naceService.postNaceData(naceDTOBody);
		logger.info("Exiting postNaceData() with response: "+ response);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
}
