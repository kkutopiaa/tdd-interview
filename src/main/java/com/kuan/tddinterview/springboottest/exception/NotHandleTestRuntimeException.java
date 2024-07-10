package com.kuan.tddinterview.springboottest.exception;

public class NotHandleTestRuntimeException extends RuntimeException {
    public NotHandleTestRuntimeException(String message) {
        super(message);
    }
}
