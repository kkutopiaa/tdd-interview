package com.kuan.tddinterview.springboottest;

import com.kuan.tddinterview.springboottest.controller.ApiError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidationTest {

    private final String expectedContent = "test";
    private final String expectedEmail = "123@163.com";
    private final String expectedPhone = "18545678900";
    private final int expectedNumber = 99;
    @Resource
    TestRestTemplate testRestTemplate;

    private final String api = "/api/v1/validation";


    @Nested
    class HappyPath {

        @Test
        public void givenPostApi_whenNullParam_thenReceiveOk() {
            ResponseEntity<TestValidationResponse> response = getResponseFromRequestWithValidParam();
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        public void givenPostApi_whenValidParam_thenReceiveNotNullResponse() {
            TestValidationResponse body = getBodyFromRequestWithValidParam();
            Assertions.assertNotNull(body);
        }

        @Test
        public void givenPostApi_whenValidParam_thenReceiveCorrectContentWithIntegerType() {
            TestValidationResponse body = getBodyFromRequestWithValidParam();
            Assertions.assertEquals(expectedNumber, body.getNumber());
        }

        @Test
        public void givenPostApi_whenValidParam_thenReceiveCorrectContentWithStringType() {
            TestValidationResponse body = getBodyFromRequestWithValidParam();
            Assertions.assertEquals(expectedContent, body.getContent());
        }

        private TestValidationResponse getBodyFromRequestWithValidParam() {
            ResponseEntity<TestValidationResponse> response = getResponseFromRequestWithValidParam();
            return response.getBody();
        }

        private ResponseEntity<TestValidationResponse> getResponseFromRequestWithValidParam() {
            TestValidationRequest build = TestValidationRequest.builder().number(expectedNumber).content(expectedContent).email(expectedEmail).phone(expectedPhone).build();
            return request(build);
        }

        private ResponseEntity<TestValidationResponse> request(TestValidationRequest build) {
            HttpEntity<TestValidationRequest> request = new HttpEntity<>(build);
            return testRestTemplate.exchange(api, HttpMethod.POST, request, TestValidationResponse.class);
        }
    }


    @Nested
    class SadPath {

        @ParameterizedTest
        @CsvSource(value = {
                "-1",
                "0",
                "-99999",
        })
        public void givenPostApi_whenInValidNumberParam_thenThrowException(Integer num) {
            TestValidationRequest build = TestValidationRequest.builder().number(num).content(expectedContent).email(expectedEmail).phone(expectedPhone).build();
            HttpEntity<TestValidationRequest> request = new HttpEntity<>(build);
            ResponseEntity<ApiError> response = testRestTemplate.exchange(api, HttpMethod.POST, request, ApiError.class);
            Assertions.assertEquals("数量需要为正整数！", Objects.requireNonNull(response.getBody()).getFiledErrorMessages().get("number"));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "1",
                "1234567",
        })
        public void givenPostApi_whenInValidContentParam_thenThrowException(String content) {
            TestValidationRequest build = TestValidationRequest.builder().number(expectedNumber).content(content).email(expectedEmail).phone(expectedPhone).build();
            HttpEntity<TestValidationRequest> request = new HttpEntity<>(build);
            ResponseEntity<ApiError> response = testRestTemplate.exchange(api, HttpMethod.POST, request, ApiError.class);
            Assertions.assertEquals("内容长度需要为2-6之间！", Objects.requireNonNull(response.getBody()).getFiledErrorMessages().get("content"));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "124#12334444",
                "asdfaad.ccccsa",
        })
        public void givenPostApi_whenInValidEmailParam_thenThrowException(String email) {
            TestValidationRequest build = TestValidationRequest.builder().number(expectedNumber).content(expectedContent).email(email).phone(expectedPhone).build();
            HttpEntity<TestValidationRequest> request = new HttpEntity<>(build);
            ResponseEntity<ApiError> response = testRestTemplate.exchange(api, HttpMethod.POST, request, ApiError.class);
            Assertions.assertEquals("邮箱格式错误！", Objects.requireNonNull(response.getBody()).getFiledErrorMessages().get("email"));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "124#12334444",
                "asdfaad.ccccsa",
        })
        public void givenPostApi_whenInValidPhoneParam_thenThrowException(String phone) {
            TestValidationRequest build = TestValidationRequest.builder().number(expectedNumber).content(expectedContent).email(expectedEmail).phone(phone).build();
            HttpEntity<TestValidationRequest> request = new HttpEntity<>(build);
            ResponseEntity<ApiError> response = testRestTemplate.exchange(api, HttpMethod.POST, request, ApiError.class);
            Assertions.assertEquals("电话格式错误！", Objects.requireNonNull(response.getBody()).getFiledErrorMessages().get("phone"));
        }
    }

}
