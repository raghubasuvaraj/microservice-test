package com.sta.dc.parent.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * To create a common response model for all the API response.
 * 
 * @author r@ghu
 *
 * @param <T>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppResponse<T> {

	/* Generic data which will adopt to the different resources. */
	private T data;

	/** The success/error message of the API requested. */
	private String message;

	/* The parameter which indicates the status of API response. */
	private boolean success;

	/* The application specific error codes. */
	private String errorCode;

	/**
	 * The hateoas resource link path of the API will display
	 * <p>
	 * To-Do: add dependency to pom.xml
	 */
	private String path;
}
