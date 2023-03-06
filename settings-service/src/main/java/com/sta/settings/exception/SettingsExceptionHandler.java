package com.sta.settings.exception;

import com.sta.settings.dto.APIErrorResponse;
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
public class SettingsExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SettingsException.class)
    public ResponseEntity<APIErrorResponse> handleSettingsExceptions(SettingsException se) {

        if(se != null &&se.getError() != null) {
            log.error(ExceptionUtils.getStackTrace(se.getError()));
        }
        APIErrorResponse response = new APIErrorResponse();
        response.setErrorCode(se.getErrorCode());
        response.setErrorMessage(se.getMessage());

        if(se.getStatusCode() == null){
            se.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,se.getStatusCode());

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<APIErrorResponse> handleExceptions(Exception e) {

        if(e != null) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        APIErrorResponse response = new APIErrorResponse();
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        response.setErrorMessage("Something went wrong, please try after some time.");

        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
