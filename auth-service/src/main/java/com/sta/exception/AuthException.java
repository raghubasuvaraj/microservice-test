package com.sta.exception;

import com.sta.enums.ErrorCodes;

import lombok.Getter;
import lombok.Setter;

/**
 * @author r@ghu
 *
 */
@Getter
@Setter
public class AuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer errorCode;
	private String message;

	public AuthException(String message) {
		super(message);
		this.message = message;
	}

	public AuthException(Integer error, String message) {
		super(message);
		this.errorCode = error;
		this.message = message;
	}

	public AuthException(Integer error, Exception ex) {
		super(ex);
		this.errorCode = error;
		this.message = ex.getMessage();
	}

	public AuthException(Exception ex) {
		super(ex);
		this.message = ex.getMessage();
	}

	public AuthException(ErrorCodes errorCode, String message) {
		super(message);
		this.errorCode = errorCode.getCode();
		this.message = message;
	}

	public AuthException(ErrorCodes errorCode, Exception ex) {
		super(ex);
		this.errorCode = errorCode.getCode();
		this.message = ex.getCause().getMessage();

	}

}
