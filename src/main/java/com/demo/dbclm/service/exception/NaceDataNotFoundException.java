package com.demo.dbclm.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is Exception class used when NACH data does not exist for provided order id.
 * 
 * @author 7338877
 *
 */

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class NaceDataNotFoundException extends RuntimeException {

	private static final Logger logger = LoggerFactory.getLogger(NaceDataNotFoundException.class);
	
	public NaceDataNotFoundException(String message) {
		super(message);
		logger.info("Inside NaceDataNotFoundException(): "+ message);
	}
}
