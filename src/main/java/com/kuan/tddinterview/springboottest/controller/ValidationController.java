package com.kuan.tddinterview.springboottest.controller;


import com.kuan.tddinterview.springboottest.TestValidationRequest;
import com.kuan.tddinterview.springboottest.TestValidationResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/validation")
public class ValidationController {

    @PostMapping
    public TestValidationResponse validation(@Valid @RequestBody TestValidationRequest request) {
        return new ModelMapper().map(request, TestValidationResponse.class);
    }

}
