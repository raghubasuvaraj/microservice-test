package com.sta.frontDesk.exception;

public class TestServiceException extends RuntimeException{
    public TestServiceException() {
        super();
    }
    public TestServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public TestServiceException(String message) {
        super(message);
    }
    public TestServiceException(Throwable cause) {
        super(cause);
    }

}


