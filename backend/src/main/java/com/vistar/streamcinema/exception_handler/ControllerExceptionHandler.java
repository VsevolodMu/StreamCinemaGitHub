package com.vistar.streamcinema.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleException(AccessDeniedException exception) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.FORBIDDEN,
                "access denied",
                exception.getClass(),
                exception.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleException(AuthenticationException exception) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.UNAUTHORIZED,
                "need token to access",
                exception.getClass(),
                exception.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exception) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                "Incorrect password",
                exception.getClass(),
                exception.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(UsernameNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                "this username is not found",
                exception.getClass(),
                exception.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<ExceptionResponse> handleException(InvalidAccountException exception) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.GONE,
                "this username is deleted by himself",
                exception.getClass(),
                exception.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

}
