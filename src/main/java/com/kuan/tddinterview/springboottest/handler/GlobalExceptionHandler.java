package com.kuan.tddinterview.springboottest.handler;

import com.kuan.tddinterview.springboottest.controller.ApiError;
import com.kuan.tddinterview.springboottest.exception.TestRuntimeException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = TestRuntimeException.class)
    public String handleTestRuntimeException(TestRuntimeException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        Map<String, String> filedErrorMessages = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, e -> e.getDefaultMessage()));

        return new ApiError("validate errors", filedErrorMessages);
    }


}
