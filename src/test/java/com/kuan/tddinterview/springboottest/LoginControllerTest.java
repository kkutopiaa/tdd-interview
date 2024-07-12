package com.kuan.tddinterview.springboottest;


import com.kuan.tddinterview.springboottest.controller.ApiError;
import com.kuan.tddinterview.springboottest.model.User;
import com.kuan.tddinterview.springboottest.repository.UserRepository;
import com.kuan.tddinterview.springboottest.serivce.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @Resource
    TestRestTemplate restTemplate;

    private final String api = "/api/v1/login";

    @Resource
    UserRepository userRepository;

    @Resource
    UserService userService;

    @BeforeEach
    public void cleanup() {
        userRepository.deleteAll();
        restTemplate.getRestTemplate().getInterceptors().clear();

    }

    @Nested
    class SadPath {

        @Test
        public void givenPostLogin_whenWithoutUserCredentials_thenReceiveUnauthorized() {
            ResponseEntity<Object> response = login(Object.class);
            Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        public void givenPostLogin_whenWithIncorrectUserCredentials_thenReceiveUnauthorized() {
            unAuthenticate();
            ResponseEntity<Object> response = login(Object.class);
            Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }


        @Test
        public void givenPostLogin_whenWithIncorrectUserCredentials_thenReceiveApiError() {
            ResponseEntity<ApiError> response = login(ApiError.class);
            Assertions.assertEquals(api, Objects.requireNonNull(response.getBody()).getUrl());
        }


        @Test
        public void givenPostLogin_whenWithIncorrectUserCredentials_thenReceiveApiErrorWithoutFiledErrorMessages() {
            ResponseEntity<String> response = login(String.class);
            Assertions.assertFalse(Objects.requireNonNull(response.getBody()).contains("filedErrorMessages"));
        }

        @Test
        public void givenPostLogin_whenWithIncorrectUserCredentials_thenReceiveUnauthorizedWithoutWWWAuthenticationHeader() {
            unAuthenticate();
            ResponseEntity<Object> response = login(Object.class);
            Assertions.assertFalse(Objects.requireNonNull(response.getHeaders()).containsKey("WWW-Authenticate"));
        }

    }

    @Test
    public void givenPostLogin_whenWithValidUserCredentials_thenReceiveOk() {
        User user = User.builder().displayName("display-name-for-user").username("test-user").password("passw0rd").build();
        userService.save(user);
        authenticate();
        ResponseEntity<Object> response = login(Object.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void givenPostLogin_whenWithValidUserCredentials_thenReceiveLoggedInUserId() {
        User user = User.builder().displayName("display-name-for-user").username("test-user").password("passw0rd").build();
        User inDB = userService.save(user);
        authenticate();
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(api, HttpMethod.POST, null,
                new ParameterizedTypeReference<Map<String, Object>>() {
                });
        Assertions.assertEquals(inDB.getId(), Long.valueOf(Objects.requireNonNull(response.getBody()).get("id") + ""));
    }


    private void unAuthenticate() {
        restTemplate.getRestTemplate()
                .getInterceptors()
                .add(new BasicAuthenticationInterceptor("test-user", "whatever"));
    }


    private void authenticate() {
        restTemplate.getRestTemplate()
                .getInterceptors()
                .add(new BasicAuthenticationInterceptor("test-user", "passw0rd"));
    }


    private <T> ResponseEntity<T> login(Class<T> responseType) {
        return restTemplate.postForEntity(api, null, responseType);
    }


}
