package com.kuan.tddinterview.springboottest.handler;

import com.kuan.tddinterview.springboottest.controller.ApiError;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class ErrorHandler implements ErrorController {

    @Resource
    ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    ApiError handleError(WebRequest webRequest) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        String message = (String) attributes.get("error");
        String url = (String) attributes.get("path");
        return new ApiError(url, message, null);
    }

    public String getErrorPath() {
        return "/error";
    }


}
