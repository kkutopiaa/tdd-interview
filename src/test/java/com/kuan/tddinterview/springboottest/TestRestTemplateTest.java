package com.kuan.tddinterview.springboottest;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 测试： TestRestTemplate提交数据的各种模式（get、post、put、delete）
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplateTest {

    private final String api = "/api/v1/test";

    @Resource
    TestRestTemplate restTemplate;

    @Test
    public void givenGetApi_whenUseForEntity_thenReceiveOk() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(api, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void givenGetApi_whenUseForEntity_thenReceiveCorrectContent() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(api, String.class);
        Assertions.assertEquals("from get", responseEntity.getBody());
    }

    @Test
    public void givenPostApi_whenUseForEntity_thenReceiveOk() {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(api, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void givenPostApi_whenUseForEntity_thenReceiveCorrectContent() {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(api, null, String.class);
        Assertions.assertEquals("from post", responseEntity.getBody());
    }

    @Test
    public void givenPutApi_whenUseForExchange_thenReceiveOk() {
        ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.PUT, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void givenPutApi_whenUseForExchange_thenReceiveCorrectContent() {
        ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.PUT, null, String.class);
        Assertions.assertEquals("from put", response.getBody());
    }

    @Test
    public void givenDeleteApi_whenUseForExchange_thenReceiveOk() {
        ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.DELETE, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void givenDeleteApi_whenUseForExchange_thenReceiveCorrectContent() {
        ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.DELETE, null, String.class);
        Assertions.assertEquals("from delete", response.getBody());
    }


}
