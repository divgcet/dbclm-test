package com.demo.dbclm.service.exception;

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

	public NaceIdAlreadyExistsException(String message) {
		super(message);
	}
}
