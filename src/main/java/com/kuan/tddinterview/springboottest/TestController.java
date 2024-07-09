package com.kuan.tddinterview.springboottest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping
    public String get() {
        return "from get";
    }

    @PostMapping
    public String post() {
        return "from post";
    }

    @PutMapping
    public String put() {
        return "from put";
    }


    @DeleteMapping
    public String delete() {
        return "from delete";
    }

}
