package com.demo.dbclm.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is Exception class used when NACH data already exist for provided order id.
 * 
 * @author 7338877
 *
 */

@ResponseStatus(code= HttpStatus.CONFLICT)
public class NaceIdAlreadyExistsException extends RuntimeException {

	private static final Logger logger = LoggerFactory.getLogger(NaceIdAlreadyExistsException.class);
	
	public NaceIdAlreadyExistsException(String message) {
		super(message);
		logger.info("Inside NaceIdAlreadyExistsException(): "+ message);
	}
}
