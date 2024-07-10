package com.kuan.tddinterview.springboottest.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private String errorMessage;

    private Map<String, String> filedErrorMessages;

}
