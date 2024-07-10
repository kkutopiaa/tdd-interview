package com.kuan.tddinterview.springboottest;

import com.kuan.tddinterview.springboottest.exception.NotHandleTestRuntimeException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GlobalExceptionTest {

    private final String api = "/api/v1/test";
    private final String expectedErrorMessage = "from not handle";

    @Resource
    TestRestTemplate testRestTemplate;

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

    @Test
    public void givenGetApi_whenOccurException_thenThrowException() {
        ResponseEntity<String> response =
                testRestTemplate.exchange(api + "/occur-exception", HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void should_catch_not_handle_exception_from_service() {
        Assertions.assertThrows(NotHandleTestRuntimeException.class, this::occurException, expectedErrorMessage);

    }

    private void occurException() {
        throw new NotHandleTestRuntimeException(expectedErrorMessage);
    }




}
