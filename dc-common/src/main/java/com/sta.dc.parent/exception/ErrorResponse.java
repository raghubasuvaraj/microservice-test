package com.sta.dc.parent.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Creating an object to hold the error messages.
 * @author r@ghu
 *
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorResponse {
	
	private int errorCode;

	private String message;

	private Boolean success;

	private Object errorData;
	
	public ErrorResponse(int errorCode, String message, boolean success) {
		this.errorCode = errorCode;
		this.message = message;
		this.success = success;
	}
	
}
