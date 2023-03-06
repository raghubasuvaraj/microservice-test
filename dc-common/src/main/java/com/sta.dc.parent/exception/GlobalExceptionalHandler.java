package com.sta.dc.parent.exception;

import com.sta.dc.parent.enums.ErrorCodes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author r@ghu
 *
 */
@ControllerAdvice
public class GlobalExceptionalHandler {

	/**
	 * Handle the error throw from the custom exceptional handler.
	 * 
	 * @param e
	 * @param response
	 * @return
	 */
	@ExceptionHandler(value = AppException.class)
	public ErrorResponse handleInstituteServiceException(AppException e, HttpServletResponse response) {
		response.setStatus(e.getErrorCode());
		return new ErrorResponse(e.getErrorCode(), e.getMessage(), false);
	}

	/**
	 * Create a custom message for the validation message by the spring boot
	 * validation. Check the each fields which consider as @NotNull, @NotBlank
	 * or @NotEmpty
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		StringBuilder builder = new StringBuilder();
		for (FieldError fError : fieldErrors) {
			if (!builder.toString().isEmpty()) {
				builder.append(", ");
			}
			builder.append(fError.getField());
			builder.append(" ");
			builder.append(fError.getDefaultMessage());
		}
		String message = StringUtils.capitalize(builder.toString());
		return new ErrorResponse(ErrorCodes.BAD_REQUEST.getCode(), message, false);
	}

	/**
	 * To handle the violation in the entity level in case of any constrain
	 * violation.
	 * <p>
	 * This is in the part of javax.validation
	 * 
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
		String message = e.getCause().getMessage();
		if (message != null && message.contains("=")) {
			String[] messages = message.split("=");
			if (messages.length == 2) {
				String value = messages[1];
				int startIndex = value.indexOf('(') + 1;
				int endIndex = value.lastIndexOf(')');
				value = value.substring(startIndex, endIndex);
				if (value.contains(",")) {
					String[] values = value.split(",");
					message = StringUtils.capitalize(values[0]) + " already exists";
				} else {
					message = StringUtils.capitalize(value) + " already exists";
				}

			}
		}
		return new ErrorResponse(ErrorCodes.CONSTRAINT_VIOLATION.getCode(),
				(message == null ? "Constraint violation" : message), false);
	}

	/**
	 * Exception thrown when an attempt to insert or update data results in
	 * violation of an integrity constraint. Note that this is not purely a
	 * relational concept; unique primary keys are required by most database types.
	 * <p>
	 * This is the part spring-boot-starter-data-jpa
	 * 
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ErrorResponse handleDataIntegrityException(DataIntegrityViolationException e) {
		String message = e.getCause().getMessage();
		if (message != null && message.contains("=")) {
			String[] messages = message.split("=");
			if (messages.length == 2) {
				String value = messages[1];
				int startIndex = value.indexOf('(') + 1;
				int endIndex = value.lastIndexOf(')');
				value = value.substring(startIndex, endIndex);
				if (value.contains(",")) {
					String[] values = value.split(",");
					message = StringUtils.capitalize(values[0]) + " already exists";
				} else {
					message = StringUtils.capitalize(value) + " already exists";
				}

			}
		}
		return new ErrorResponse(ErrorCodes.CONSTRAINT_VIOLATION.getCode(),
				(message == null ? "Constraint violation" : message), false);
	}
}
