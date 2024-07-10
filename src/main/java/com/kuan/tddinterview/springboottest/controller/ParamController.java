package com.kuan.tddinterview.springboottest.controller;


import com.kuan.tddinterview.springboottest.TestRequestBody;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/param")
public class ParamController {

    @GetMapping("/request-param")
    public String withRequestParam(
            @RequestParam(name = "param", required = false, defaultValue = "default") String param) {
        return param;
    }

    @GetMapping("/path-variable/{param}")
    public String withPathVariable(@PathVariable(name = "param", required = false) String param) {
        return param;
    }

    @PostMapping("/request-body")
    public String withRequestBody(@RequestBody(required = false) TestRequestBody requestBody) {
        return requestBody == null ? null : requestBody.getParam();
    }

    @GetMapping("/request-header")
    public String withRequestParam(HttpServletRequest request) {
        return request.getHeader("param");
    }


}
