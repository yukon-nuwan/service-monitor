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

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RecordFoundException extends BaseException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5040616152377126504L;

	public RecordFoundException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public RecordFoundException(String errorCode, String errorDescription) {
		super(errorCode, errorDescription);
	}

	public RecordFoundException(String errorCode, String errorDescription, Throwable cause) {
		super(errorCode, errorDescription, cause);
	}

}
