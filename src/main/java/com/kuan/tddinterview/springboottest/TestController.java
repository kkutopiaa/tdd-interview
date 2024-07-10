package com.kuan.tddinterview.springboottest;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping
    public String get() {
        return "from get";
    }

    @GetMapping(path = "/type")
    public TestObject<TestGenericType> getType() {
        return new TestObject<>(Arrays.asList(new TestGenericType(), new TestGenericType()));
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
