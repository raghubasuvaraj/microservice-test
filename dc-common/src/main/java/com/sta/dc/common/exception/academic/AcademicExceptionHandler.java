package com.sta.dc.common.exception.academic;

import com.sta.dc.common.exception.APIErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class AcademicExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AcademicException.class)
    public ResponseEntity<APIErrorResponse> handleSettingsExceptions(AcademicException ae) {

        if (ae != null && ae.getError() != null) {
            log.error(ExceptionUtils.getStackTrace(ae.getError()));
        }
        APIErrorResponse response = new APIErrorResponse();
        response.setErrorCode(ae.getErrorCode());
        response.setErrorMessage(ae.getMessage());

        if (ae.getStatusCode() == null) {
            ae.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, ae.getStatusCode());

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<APIErrorResponse> handleExceptions(Exception e) {

        if (e != null) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        APIErrorResponse response = new APIErrorResponse();
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        response.setErrorMessage("Something went wrong, please try after some time.");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
