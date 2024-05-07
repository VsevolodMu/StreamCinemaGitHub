package com.vistar.streamcinema.exception_handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

    private final HttpStatus status;
    private final String message;
    private final Class<? extends Exception> ex;
    private final String exceptionMessage;
}

