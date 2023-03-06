package com.sta.settings.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * This class will be removed by the dependency common-utilities from this
 * project(once it is deployed as a dependency)
 * 
 * @author r@ghu
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettingsException extends RuntimeException {

	private static final long serialVersionUID = -67545750766050093L;

	private String errorCode;

	private String message;

	private HttpStatus statusCode;

	private Throwable error;

}
