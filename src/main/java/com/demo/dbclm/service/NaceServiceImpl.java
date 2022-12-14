package com.demo.dbclm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dbclm.domain.NaceData;
import com.demo.dbclm.repository.NaceRepository;
import com.demo.dbclm.service.exception.NaceDataNotFoundException;
import com.demo.dbclm.service.exception.NaceIdAlreadyExistsException;

/**
 * This is the service class used for applying any business logic on data fetched from 
 * repository layer.
 * 
 * 
 * @author 7338877
 *
 */

@Service
public class NaceServiceImpl implements NaceService{
	
	private static final Logger logger = LoggerFactory.getLogger(NaceServiceImpl.class);
	
	@Autowired
    private NaceRepository naceRepository;
	
	/**
	 * This method is used to get the NACH data for provided order id and deleting call to repository layer.
	 * 
	 * @param orderId 
	 * @return
	 */
	public NaceData getNaceDataByorderId(long orderId) {
		logger.info("Inside getNaceDataByorderId() with orderId: "+ orderId);
		return naceRepository.findById(orderId).orElseThrow(
                ()-> new NaceDataNotFoundException(
                        "NO NACE data PRESENT WITH ID: " + orderId));
	}

	/**
	 * This method is used to post the NACH data with provided input as insert record to DB.
	 * 
	 * @param naceDTO object to post data to DB
	 * @return
	 */
	
	public String postNaceData(NaceData naceDTO) {
		logger.info("Inside postNaceData() with naceDTO: "+ naceDTO);
		NaceData existingNaceData
        = naceRepository.findById(naceDTO.getOrderId())
              .orElse(null);
	    if (existingNaceData == null) {
	    	naceRepository.save(naceDTO);
	    	logger.info("Inside postNaceData(), NACE data added successfully");
	        return "NACE data added successfully";
	    }
	    else
	        throw new NaceIdAlreadyExistsException(
	            "NACE data already exists with ID: " + naceDTO.getOrderId());
		}

}
