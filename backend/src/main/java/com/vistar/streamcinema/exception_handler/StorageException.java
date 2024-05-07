package com.vistar.streamcinema.exception_handler;


public class StorageException extends RuntimeException {

    public StorageException(Exception ex) {
        super(ex);
    }
}