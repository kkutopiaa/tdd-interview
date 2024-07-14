package com.kuan.tddinterview.springboottest;

import com.kuan.tddinterview.springboottest.controller.ApiError;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GlobalExceptionTest {
    private final String api = "/api/v1/test";

    @Resource
    TestRestTemplate testRestTemplate;

    @Nested
    class HappyPath {
        @Test
        public void givenGetApi_whenHandleException_thenReceiveOk() {
            ResponseEntity<String> response =
                    testRestTemplate.exchange(api + "/handle-exception", HttpMethod.GET, null, String.class);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        public void givenGetApi_whenHandleException_thenReceiveCorrectContent() {
            ResponseEntity<String> response =
                    testRestTemplate.exchange(api + "/handle-exception", HttpMethod.GET, null, String.class);
            Assertions.assertEquals("something test runtime exception by diy", response.getBody());
        }
    }

    @Nested
    class SadPath {
        @Test
        public void givenGetApi_whenOccurException_thenReceive500Status() {
            ResponseEntity<String> response =
                    testRestTemplate.exchange(api + "/occur-exception", HttpMethod.GET, null, String.class);
            Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        }

        @Test
        public void givenGetApi_whenOccurException_thenReceiveTargetUrl() {
            ResponseEntity<ApiError> response =
                    testRestTemplate.exchange(api + "/occur-exception", HttpMethod.GET, null, ApiError.class);
            Assertions.assertEquals(api + "/occur-exception", Objects.requireNonNull(response.getBody()).getUrl());
        }
    }
}
