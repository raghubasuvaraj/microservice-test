package com.sta.dc.common.exception.academic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcademicException extends RuntimeException {

    private static final long serialVersionUID = -67545750766050093L;

    private String errorCode;

    private String message;

    private HttpStatus statusCode;

    private Throwable error;

}
