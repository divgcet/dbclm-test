package com.demo.dbclm.service.exception;

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

	public NaceDataNotFoundException(String message) {
		super(message);
	}
}
