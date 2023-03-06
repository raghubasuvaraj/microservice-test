package com.sta.dc.parent.exception;

import com.sta.dc.parent.enums.ErrorCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {

	private static final long serialVersionUID = -5752162648440049525L;
	
	private Integer errorCode;

	private String message;
	
	public AppException(String message) {
		super(message);
		this.message = message;
	}
	
	public AppException(Integer errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public AppException(ErrorCodes errorCode, String message) {
		super(message);
		this.errorCode = errorCode.getCode();
		this.message = message;
	}
	
	public AppException(Integer errorCode, Exception ex) {
		super(ex);
		this.errorCode = errorCode;
		this.message = ex.getMessage();
	}
	
	public AppException(Exception ex) {
		super(ex);
		this.message = ex.getMessage();
	}
}
