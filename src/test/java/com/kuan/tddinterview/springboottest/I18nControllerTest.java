package com.kuan.tddinterview.springboottest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class I18nControllerTest {

    private final String api = "/api/v1/messages";

    @Resource
    TestRestTemplate restTemplate;

    @Test
    public void givenGetMessages_whenLangIsZh_thenReceiveChinese() {
        HttpEntity<String> request = new HttpEntity<>(null,
                new MultiValueMapAdapter<>(Map.of(HttpHeaders.ACCEPT_LANGUAGE, List.of("zh-CN"))));
        ResponseEntity<String> response =
                restTemplate.exchange(api + "?param=测试", HttpMethod.GET, request, String.class);
        Assertions.assertEquals("你好世界，测试", response.getBody());
    }


    @Test
    public void givenGetMessages_whenLangIsUs_thenReceiveEnglish() {
        HttpEntity<String> request = new HttpEntity<>(null,
                new MultiValueMapAdapter<>(Map.of(HttpHeaders.ACCEPT_LANGUAGE, List.of("en_US"))));
        ResponseEntity<String> response =
                restTemplate.exchange(api + "?param=test", HttpMethod.GET, request, String.class);
        Assertions.assertEquals("helloWorld,test", response.getBody());
    }

    @Test
    public void givenGetMessages_whenLangIsNull_thenReceiveChinese() {
        ResponseEntity<String> response =
                restTemplate.exchange(api + "?param=test", HttpMethod.GET, null, String.class);
        Assertions.assertEquals("你好世界，test", response.getBody());
    }

    @Test
    public void givenGetMessages_whenLangIsNullAndParamIsNull_thenReceiveChineseAndDefaultParam() {
        ResponseEntity<String> response =
                restTemplate.exchange(api, HttpMethod.GET, null, String.class);
        Assertions.assertEquals("你好世界，default", response.getBody());
    }

}
