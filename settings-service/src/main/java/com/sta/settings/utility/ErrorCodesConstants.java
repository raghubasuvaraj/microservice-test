package com.sta.settings.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodesConstants {
	
	//Informational responses (100–199)
	public static final String CONSTRAINT_VIOLATION = "CONSTRAINT_VIOLATION"; 

	// Successful responses (200–299)
	public static final String OK = "OK";
	
	public static final String CREATED = "CREATED";

	// Client error responses (400–499)
	public static final String BAD_REQUEST = HttpStatus.BAD_REQUEST.toString();

	public static final String UNAUTHORIZED = "UNAUTHORIZED"; 

	public static final String PAYMENT_REQUIRED = "PAYMENT_REQUIRED"; 

	public static final String FORBIDDEN = "FORBIDDEN";

	//public static final String NOT_FOUND = HttpStatus.INTERNAL_SERVER_ERROR.toString(); 
	
	public static final String NOT_FOUND = HttpStatus.NOT_FOUND.toString();

	public static final String METHOD_NOT_ALLOWED = "METHOD_NOT_ALLOWED"; 

	public static final String NOT_ACCEPTABLE = "NOT_ACCEPTABLE"; 

	public static final String PROXY_AUTHENTICATION_REQUIRED = "";

	public static final String REQUEST_TIMEOUT = "REQUEST_TIMEOUT"; 

	public static final String CONFLICT = "CONFLICT"; 

	public static final String GONE = "GONE"; 

	public static final String LENGTH_REQUIRED = "LENGTH_REQUIRED"; 

	public static final String PRECONDITION_FAILED = "PRECONDITION_FAILED";

	public static final String PAYLOAD_TOO_LARGE = "PAYLOAD_TOO_LARGE"; 

	public static final String REQUEST_URI_TOO_LONG = "REQUEST_URI_TOO_LONG"; 

	public static final String UNSUPPORTED_MEDIA_TYPE = "UNSUPPORTED_MEDIA_TYPE";

	public static final String REQUESTED_RANGE_NOT_SATISFIABLE = "REQUESTED_RANGE_NOT_SATISFIABLE"; 

	public static final String EXPECTATION_FAILED = "EXPECTATION_FAILED"; 

	public static final String I_AM_A_TEAPOT = "I_AM_A_TEAPOT"; 

	public static final String MISDIRECTED_REQUEST = "MISDIRECTED_REQUEST";

	public static final String UNPROCESSABLE_ENTITY = "UNPROCESSABLE_ENTITY"; 

	public static final String LOCKED = "LOCKED"; 

	public static final String FAILED_DEPENDENCY = "FAILED_DEPENDENCY"; 

	public static final String UPGRADE_REQUIRED = "UPGRADE_REQUIRED"; 

	public static final String PRECONDITION_REQUIRED = "PRECONDITION_REQUIRED";

	public static final String TOO_MANY_REQUESTS = "TOO_MANY_REQUESTS"; 

	public static final String REQUEST_HEADER_FIELDS_TOO_LARGE = "REQUEST_HEADER_FIELDS_TOO_LARGE"; 

	public static final String CONNECTION_CLOSED_WITHOUT_RESPONSE = "CONNECTION_CLOSED_WITHOUT_RESPONSE";

	public static final String UNAVAILABLE_FOR_LEGAL_REASONS = "UNAVAILABLE_FOR_LEGAL_REASONS"; 

	public static final String CLIENT_CLOSED_REQUEST = "CLIENT_CLOSED_REQUEST";

	// Server error responses (500–599)
	public static final String INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.toString();
}
