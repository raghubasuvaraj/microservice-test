package com.sta.fee.exception;

public class FeeServiceException extends RuntimeException{
    public FeeServiceException() {
        super();
    }
    public FeeServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public FeeServiceException(String message) {
        super(message);
    }
    public FeeServiceException(Throwable cause) {
        super(cause);
    }

}


