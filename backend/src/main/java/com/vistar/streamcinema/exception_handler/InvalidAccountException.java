package com.vistar.streamcinema.exception_handler;

import org.springframework.security.core.AuthenticationException;

public class InvalidAccountException extends AuthenticationException {
    public InvalidAccountException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidAccountException(String msg) {
        super(msg);
    }
}

