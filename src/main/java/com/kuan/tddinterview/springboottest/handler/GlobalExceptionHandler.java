package com.kuan.tddinterview.springboottest.handler;

import com.kuan.tddinterview.springboottest.exception.TestRuntimeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = TestRuntimeException.class)
    public String handleTestRuntimeException(TestRuntimeException exception) {
        return exception.getMessage();
    }


}
