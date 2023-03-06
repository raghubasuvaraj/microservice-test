package com.sta.exception;

import lombok.Data;

/**
 * Model of error response. 
 * @author r@ghu
 *
 */
@Data
public class ErrorResponse {
	
	private int errorCode;

	private String message;

	private Boolean success;

	private Object errorData;
}
