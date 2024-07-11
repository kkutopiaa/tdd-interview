package com.kuan.tddinterview.springboottest;

import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.MultiValueMapAdapter;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReceiveControllerParamTest {

    @Resource
    TestRestTemplate restTemplate;

    final String expectedParam = "qxk";
    private final String prefix = "/api/v1/param";

    @Nested
    class RequestParamAnnotation {
        private final String api = prefix + "/request-param";

        @Test
        public void givenGetApi_whenUseRequestParamAnnotation_thenReceiveOk() {
            ResponseEntity<String> response = restTemplate.exchange(api + "?param=" + expectedParam,
                    HttpMethod.GET, null, String.class);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        public void givenGetApi_whenUseRequestParamAnnotation_thenReceiveCorrectParam() {
            ResponseEntity<String> response = restTemplate.exchange(api + "?param=" + expectedParam,
                    HttpMethod.GET, null, String.class);
            Assertions.assertEquals(expectedParam, response.getBody());
        }

        @Test
        public void givenGetApi_whenUseRequestParamAnnotation_thenReceiveDefaultParam() {
            ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.GET, null, String.class);
            Assertions.assertEquals("default", response.getBody());
        }
    }

    @Nested
    class PathVariableAnnotation {
        private final String api = prefix + "/path-variable";

        @Test
        public void givenGetApi_whenUsePathVariableAnnotation_thenReceiveOk() {
            ResponseEntity<String> response = restTemplate.exchange(api + "/" + expectedParam,
                    HttpMethod.GET, null, String.class);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        public void givenGetApi_whenUsePathVariableAnnotation_thenReceiveCorrectParam() {
            ResponseEntity<String> response = restTemplate.exchange(api + "/" + expectedParam,
                    HttpMethod.GET, null, String.class);
            Assertions.assertEquals(expectedParam, response.getBody());
        }

        @Test
        public void givenGetApiWithNotParam_whenUsePathVariableAnnotation_thenReceiveNotFound() {
            ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.GET, null, String.class);
            Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }


    @Nested
    class RequestBodyAnnotation {
        private final String api = prefix + "/request-body";

        @Test
        public void givenPostApi_whenUseRequestBodyAnnotation_thenReceiveOk() {
            HttpEntity<TestRequestBody> request = new HttpEntity<>(new TestRequestBody(expectedParam));
            ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.POST, request, String.class);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        public void givenPostApi_whenUseRequestBodyAnnotation_thenReceiveCorrectParam() {
            HttpEntity<TestRequestBody> request = new HttpEntity<>(new TestRequestBody(expectedParam));
            ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.POST, request, String.class);
            Assertions.assertEquals(expectedParam, response.getBody());
        }

        @Test
        public void givenPostApi_whenUseRequestBodyAnnotation_thenReceiveNullParam() {
            HttpEntity<TestRequestBody> request = new HttpEntity<>(null,
                    new MultiValueMapAdapter<>(Map.of(HttpHeaders.CONTENT_TYPE, List.of("application/json"))));

            ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.POST, request, String.class);
            Assertions.assertNull(response.getBody());
        }
    }

    @Nested
    class FromRequestHeader {
        private final String api = prefix + "/request-header";


        @Test
        public void givenGetApi_whenUseRequestHeader_thenReceiveOk() {
            HttpEntity<String> request = new HttpEntity<>(null,
                    new MultiValueMapAdapter<>(Map.of("param", List.of(expectedParam))));
            ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.GET, request, String.class);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        public void givenGetApi_whenUseRequestHeader_thenReceiveCorrectParam() {
            HttpEntity<String> request = new HttpEntity<>(null,
                    new MultiValueMapAdapter<>(Map.of("param", List.of(expectedParam))));
            ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.GET, request, String.class);
            Assertions.assertEquals(expectedParam, response.getBody());
        }

        @Test
        public void givenGetApi_whenUseRequestHeader_thenReceiveNullParam() {
            HttpEntity<String> request = new HttpEntity<>(null);
            ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.GET, request, String.class);
            Assertions.assertNull(response.getBody());
        }

    }

}
