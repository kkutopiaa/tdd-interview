package com.kuan.tddinterview.springboottest;

import com.kuan.tddinterview.springboottest.exception.NotHandleTestRuntimeException;
import com.kuan.tddinterview.springboottest.exception.TestRuntimeException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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

    @GetMapping("/handle-exception")
    public String handleException() {
        throw new TestRuntimeException("something test runtime exception by diy");
    }

    @GetMapping("/occur-exception")
    public String occurException() {
        throw new NotHandleTestRuntimeException("whatever");
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
