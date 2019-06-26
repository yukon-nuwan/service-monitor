/**
 * 
 */
package com.yukon.service.monitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nuwan
 *
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends BaseException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3359971066375673298L;

	public RecordNotFoundException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public RecordNotFoundException(String errorCode, String errorDescription) {
		super(errorCode, errorDescription);
	}

	public RecordNotFoundException(String errorCode, String errorDescription, Throwable cause) {
		super(errorCode, errorDescription, cause);
	}

}
