/**
 * 
 */
package com.yukon.service.monitor.exception;

/**
 * @author Nuwan
 *
 */
public class BaseException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5835848284102379354L;
	private String errorCode;
	private String errorDescription;
	
	
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	
	public BaseException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
	}
	
	public BaseException(String errorCode, String errorDescription) {
		super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
		
	}
	
	public BaseException(String errorCode, String errorDescription, Throwable cause) {
		super(errorDescription,cause);
        this.errorCode = errorCode;
	}
}
