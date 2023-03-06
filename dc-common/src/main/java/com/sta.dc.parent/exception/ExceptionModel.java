package com.sta.dc.parent.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * The {@code ExceptionModelClass}
 * <br> implements {@code Serializable}
 * <p>
 * {@code @Data} annotation is used to generate
 * <br>
 * <i>Getters, Setters, Required Arguments Constructor, toString, equals and HashCode methods</i>
 * </p>
 * 
 * @author r@ghu
 * 
 */
@Data
public class ExceptionModel implements Serializable {

	private static final long serialVersionUID = 5140914341741829544L;
	
	/* The {@code status} field used to hold HTTP Statuses. */
	private HttpStatus status;
	
	/* The {@code errors} field used to get a list of errors thrown. */
    private List<String> errors;
    
    /* The {@code timeStamp} field used to get the Date and Time the error occurred. */
    private LocalDateTime timeStamp;

    /* The {@code pathUri} field used to get the API path where the issue has occurred. */
    private String pathUri;

}
