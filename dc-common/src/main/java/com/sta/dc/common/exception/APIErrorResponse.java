package com.sta.dc.common.exception;

import lombok.Data;

import java.util.List;
@Data
public class APIErrorResponse {
    String errorCode;
    String errorMessage;
    List<String> errorMessages;
}
